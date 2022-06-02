package com.ansv.internalsoftware.repo.base;

import java.util.List;
import java.util.Map;

public interface IBaseRepository<T> {
    List<T> search(Map<String,Object> searchParam, Class<T> t);

    Long count(Map<String, Object> searchParam);
}
