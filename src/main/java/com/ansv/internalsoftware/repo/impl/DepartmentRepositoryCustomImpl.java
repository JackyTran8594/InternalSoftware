package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.repo.base.BaseCustomRepository;
import com.ansv.internalsoftware.repo.custom.DepartmentRepositoryCustom;
import com.ansv.internalsoftware.util.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DepartmentRepositoryCustomImpl extends BaseCustomRepository<Department> implements DepartmentRepositoryCustom {


    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if (count) {
            sb.append("SELECT COUNT(*) \n")
                    .append("FROM department d \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT d.* \n")
                    .append("FROM department d \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            sb.append("AND (UPPER(d.name) LIKE :txtSearch) OR" +
                    " (UPPER(d.code) LIKE :txtSearch) OR (UPPER(d.description) LIKE :txtSearch)" +
                    " (UPPER(d.note) LIKE :txtSearch) \n");
            params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toString());
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY d.id DESC"));
            } else {
                sb.append("ORDER BY d.id DESC");
            }
        }

        if (!count && paramNotNullOrEmpty(paramsSearch, "pageSize") && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
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
        return getResultList(sql, Department.class, parameters);
    }

    @Override
    public Long count(Map searchParam) {
        Map<String,Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }
}
