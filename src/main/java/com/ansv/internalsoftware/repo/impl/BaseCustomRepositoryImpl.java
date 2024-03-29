package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.repo.BaseRepositoryCustom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCustomRepositoryImpl<T> extends BaseCustomRepository<T> implements BaseRepositoryCustom<T> {

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        return null;
    }

    @Override
    public List<T> search(Map<String, Object> searchParam, Class<T> t) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getResultList(sql, t, parameters);
    }

    @Override
    public Long count(Map<String, Object> searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }
}
