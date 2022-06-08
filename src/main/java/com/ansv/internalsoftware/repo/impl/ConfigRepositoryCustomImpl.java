package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.model.Config;
import com.ansv.internalsoftware.repo.ConfigRepositoryCustom;
import com.ansv.internalsoftware.util.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigRepositoryCustomImpl extends BaseCustomRepository<Config> implements ConfigRepositoryCustom {

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if (count) {
            sb.append("SELECT COUNT(id) \n")
                    .append("FROM config os \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT r.* \n")
                    .append("FROM config os \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            sb.append("AND (UPPER(os.name) LIKE :txtSearch) OR (UPPER(os.code) LIKE :txtSearch) OR (UPPER(os.note) LIKE :txtSearch");
            params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toUpperCase());
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY os.id ASC "));
            } else {
                sb.append(" ORDER BY os.id DESC ");
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
        return getResultList(sql, Config.class, parameters);
    }

    @Override
    public Long count(Map searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }
}
