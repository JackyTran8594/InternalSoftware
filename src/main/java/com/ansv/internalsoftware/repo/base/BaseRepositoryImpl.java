package com.ansv.internalsoftware.repo.base;

import java.util.Map;

public class BaseRepositoryImpl<T> extends BaseRepository<T> implements IBaseRepository<T> {

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        return null;
    }
}
