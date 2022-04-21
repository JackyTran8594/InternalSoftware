package com.ansv.internalsoftware.repo.base;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    <T> T getSingleResult(String query, Map<String, Object> params, Class<T> clazz) {
        try {
            Query nativeQuery = entityManager.createNativeQuery(query);
            if (CollectionUtils.isEmpty(params)) {
                return clazz.cast(nativeQuery.getSingleResult());
            }
            params.forEach(nativeQuery::setParameter);
            return clazz.cast(nativeQuery.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    <T> T getSingleResult(String query, Class<T> clazz, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, clazz);
        return executeSingleResultQuery(nativeQuery, params);
    }

    private <T> T executeSingleResultQuery(Query nativeQuery, Map<String, Object> params) {
        try {
            if (CollectionUtils.isEmpty(params)) {
                return (T) nativeQuery.getSingleResult();
            }
            params.forEach(nativeQuery::setParameter);
            return (T) nativeQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    <T> List<T> getResultList(String query, String mapping, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, mapping);
        return executeListResultQuery(nativeQuery, params);
    }

    <T> List<T> getResultList(String query, Class<T> clazz, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, clazz);
        return executeListResultQuery(nativeQuery, params);
    }

    private <T> List<T> executeListResultQuery(Query nativeQuery, Map<String, Object> params) {
        try {
            if (CollectionUtils.isEmpty(params)) {
                return nativeQuery.getResultList();
            }
            params.forEach(nativeQuery::setParameter);
            return nativeQuery.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    List<Object[]> getResultList(String query, Map<String, Object> params) {
        try {
            Query nativeQuery = entityManager.createNativeQuery(query);
            if (CollectionUtils.isEmpty(params)) {
                return nativeQuery.getResultList();
            }
            params.forEach(nativeQuery::setParameter);
            return nativeQuery.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }

    }

    int executeUpdate(String query, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        if (CollectionUtils.isEmpty(params)) {
            return nativeQuery.executeUpdate();
        }
        params.forEach(nativeQuery::setParameter);
        return nativeQuery.executeUpdate();
    }

    Long getCountResult(String query, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        if (CollectionUtils.isEmpty(params)) {
            return ((Integer) nativeQuery.getSingleResult()).longValue();
        }
        params.forEach(nativeQuery::setParameter);
        return ((Integer) nativeQuery.getSingleResult()).longValue();
    }

    public abstract String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count);


}
