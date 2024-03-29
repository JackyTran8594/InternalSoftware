package com.ansv.internalsoftware.repo;

import java.util.List;
import java.util.Map;

public interface BaseRepositoryCustom<T> {

    List<T> search(Map<String,Object> searchParam, Class<T> t);

    Long count(Map<String, Object> searchParam);
}
