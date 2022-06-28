package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.model.PeriodOrder;
import com.ansv.internalsoftware.repo.PeriodOrderRepositoryCustom;
import com.ansv.internalsoftware.util.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PeriodOrderRepositoryCustomImpl extends BaseCustomRepository<PeriodOrder> implements PeriodOrderRepositoryCustom {



    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if (count) {
            sb.append("SELECT COUNT(id) \n")
                    .append("FROM period_order po \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT po.* \n")
                    .append("FROM period_order po \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            if(!DataUtils.isNullOrEmpty(paramsSearch.get("txtSearch"))) {
                sb.append("AND (UPPER(po.code) LIKE :txtSearch) OR (UPPER(po.description) LIKE :txtSearch)" +
                        " OR (UPPER(po.bank_guarantee) LIKE :txtSearch)"
                        + "OR (UPPER(po.payment_content) LIKE :txtSearch) OR (UPPER(po.address) LIKE :txtSearch)"
                        + "OR (UPPER(po.note) LIKE :txtSearch)");
                params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toUpperCase());
            }
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY po.id DESC "));
            } else {
                sb.append(" ORDER BY po.id DESC ");
            }
        }

        if(!count && paramNotNullOrEmpty(paramsSearch, "pageSize") && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
            sb.append(" OFFSET :offset ROWS");
            sb.append(" FETCH NEXT :limit ROWS ONLY");
            params.put("offset", offsetPaging(DataUtils.parseToInt(paramsSearch.get("pageNumber")), DataUtils.parseToInt(paramsSearch.get("pageSize"))));
            params.put("limit", DataUtils.parseToInt(paramsSearch.get("limit")));
        }

        return sb.toString();
    }

    @Override
    public List search(Map searchParam, Class t) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getResultList(sql, PeriodOrder.class, parameters);

    }

    @Override
    public Long count(Map searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getCountResult(sql, parameters);
    }
}
