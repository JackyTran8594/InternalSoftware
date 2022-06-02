package com.ansv.internalsoftware.repo.base;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ansv.internalsoftware.util.DataUtils;
import org.springframework.util.CollectionUtils;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <T> T getSingleResult(String query, Map<String, Object> params, Class<T> clazz) {
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

    protected <T> T getSingleResult(String query, Class<T> clazz, Map<String, Object> params) {
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

    protected <T> List<T> getResultList(String query, String mapping, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, mapping);
        return executeListResultQuery(nativeQuery, params);
    }

    protected <T> List<T> getResultList(String query, Class<T> clazz, Map<String, Object> params) {
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

    protected List<Object[]> getResultList(String query, Map<String, Object> params) {
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

    protected int executeUpdate(String query, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        if (CollectionUtils.isEmpty(params)) {
            return nativeQuery.executeUpdate();
        }
        params.forEach(nativeQuery::setParameter);
        return nativeQuery.executeUpdate();
    }

    protected Long getCountResult(String query, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        if (CollectionUtils.isEmpty(params)) {
            return ((Integer) nativeQuery.getSingleResult()).longValue();
        }
        params.forEach(nativeQuery::setParameter);
        return ((Integer) nativeQuery.getSingleResult()).longValue();
    }

    public abstract String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count);

    public String formatLike(String param) {
        return "%" + param.trim() + "%";
    }

    protected Integer offsetPaging(Integer page, Integer limit) {
        page = page == null ? 0 : page;
        limit = limit == null ? 10 : limit;
        return (page) * limit;
    }

    protected String formatSort(String sort, String defaultSort) {
        if (DataUtils.notNull(sort)) {
            List<String> items = Arrays.asList(sort.split(";"));
            return formatSort(items, defaultSort);
        }
        return defaultSort;
    }

    //    @SuppressWarnings("java:S3776")
    protected String formatSort(List<String> sort, String defaultSort) {
        if (DataUtils.notNullOrEmpty(sort)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ORDER BY ");
            if (sort.get(0).contains(",")) {
                //&sort=code,asc&sort=lastUpdateDate,desc
                String[] tmpArr;
                for (String tmp : sort) {
                    tmpArr = tmp.split(",");
                    if (tmpArr.length > 1) {
                        sb.append(" ")
                                .append(DataUtils.camelToSnake(tmpArr[0]))
                                .append(" ")
                                .append(tmpArr[1])
                                .append(",");
                    } else {
                        sb.append(" ")
                                .append(DataUtils.camelToSnake(tmpArr[0]))
                                .append(" ASC,");
                    }
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                sb.append(" ");
                return sb.toString();
            } else {
                //sort=code,asc
                for (String s : sort) {
                    sb.append(DataUtils.camelToSnake(s))
                            .append(" ");
                }
                sb.append(" ");
                return sb.toString();
            }
        }
        return defaultSort == null ? "" : String.format(" %s ", defaultSort);
    }

    public boolean paramNotNullOrEmpty(Map<String, Object> paramSearch, String key) {
        if (paramSearch.get(key) == null) {
            return false;
        }

        String data = String.valueOf(paramSearch.get(key));
        return DataUtils.notNullOrEmpty(data);
    }
}
