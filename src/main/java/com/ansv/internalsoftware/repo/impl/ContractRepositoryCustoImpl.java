package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.config.formatdate.LocalDateTimeDeserializer;
import com.ansv.internalsoftware.config.formatdate.LocalDateTimeSerializer;
import com.ansv.internalsoftware.model.Contract;
import com.ansv.internalsoftware.repo.base.BaseRepository;
import com.ansv.internalsoftware.repo.custom.ContractRepositoryCustom;
import com.ansv.internalsoftware.util.DataUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContractRepositoryCustoImpl extends BaseRepository<Contract> implements ContractRepositoryCustom {
    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if(count) {
            sb.append("SELECT COUNT(*) \n")
                    .append("FROM contract c \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT c.* \n")
                    .append("FROM contract c \n")
                    .append("WHERE 1=1 ");
        }

        if(paramsSearch.containsKey("txtSearch")) {
            sb.append("AND (UPPER(c.contractCode) LIKE :txtSearch) OR" +
                    " (UPPER(c.name) LIKE :txtSearch) OR (UPPER(c.description) LIKE :txtSearch)" +
                    " (UPPER(c.paymentContent) LIKE :txtSearch) OR (UPPER(c.bankGuarantee) LIKE :txtSearch)) " +
                    " (UPPER(c.guarantee) LIKE :txtSearch) OR (UPPER(c.note) LIKE :txtSearch)) \n");
            params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toString());
        }

        if(!count) {
            if(paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY c.id ASC"));
            } else {
                sb.append("ORDER BY c.id DESC");
            }
        }

        if(!count && paramNotNullOrEmpty(paramsSearch, "pageSize") && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
            sb.append(" OFFSET :offset ROWS")
                    .append("FETCH NEXT :limit ROW ONLY");
            params.put("offset", offsetPaging(DataUtils.parseToInt(paramsSearch.get("pageNumber")), DataUtils.parseToInt(paramsSearch.get("pageSize"))));
            params.put("limit", DataUtils.parseToInt(paramsSearch.get("limit")));
        }

        return sb.toString();
    }

    @Override
    public List search(Map searchParam, Class t) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam,parameters, false);
        return getResultList(sql, Contract.class, parameters);
    }

    @Override
    public Long count(Map searchParam) {
        Map<String,Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }
}
