package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.model.User;
import com.ansv.internalsoftware.repo.base.BaseRepository;
import com.ansv.internalsoftware.repo.custom.UserRepositoryCustom;

import java.util.Map;

public class UserRepositoryCustomImpl extends BaseRepository<User> implements UserRepositoryCustom {

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        return null;
    }
}
