package com.ansv.internalsoftware.repo.impl;

import com.ansv.internalsoftware.modal.Role;
import com.ansv.internalsoftware.repo.base.BaseRepository;
import com.ansv.internalsoftware.repo.custom.RoleRepositoryCustom;

import java.util.Map;

public class RoleRepositoryCustomImpl extends BaseRepository<Role> implements RoleRepositoryCustom {

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        return null;
    }
}
