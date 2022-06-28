package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.model.DeliveryPackage;
import com.ansv.internalsoftware.repo.DeliveryPackageRepositoryCustom;
import com.ansv.internalsoftware.util.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DeliveryPackageRepositoryCustomImpl extends BaseCustomRepository<DeliveryPackage> implements DeliveryPackageRepositoryCustom {


    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if (count) {
            sb.append("SELECT COUNT(*) \n")
                    .append("FROM delivery_package dp \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT d.* \n")
                    .append("FROM delivery_package dp \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            if(!DataUtils.isNullOrEmpty(paramsSearch.get("txtSearch"))) {
                sb.append("AND (dp.dpCode LIKE :txtSearch) OR" +
                        " (dp.poCode) LIKE :txtSearch) OR (dp.contractCode LIKE :txtSearch)" +
                        " OR (dp.description LIKE :txtSearch) " +
                        "OR (dp.address LIKE :txtSearch) \n");
                params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toString());
            }
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY dp.id DESC"));
            } else {
                sb.append("ORDER BY dp.id DESC");
            }
        }

        if (!count && paramNotNullOrEmpty(paramsSearch, "pageSize") && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
            sb.append(" OFFSET :offset ROWS ")
                    .append(" FETCH NEXT :limit ROW ONLY");
            params.put("offset", offsetPaging(DataUtils.parseToInt(paramsSearch.get("pageNumber")), DataUtils.parseToInt(paramsSearch.get("pageSize"))));
            Integer limit = !DataUtils.isNullOrEmpty(paramsSearch.get("limit")) ? DataUtils.parseToInt(paramsSearch.get("limit")) : DataUtils.parseToInt(paramsSearch.get("pageSize"));
            params.put("limit", limit);
        }
        return sb.toString();
    }

    @Override
    public List search(Map searchParam, Class t) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam,parameters, false);
        return getResultList(sql, DeliveryPackage.class, parameters);
    }

    @Override
    public Long count(Map searchParam) {
        Map<String,Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }
}
