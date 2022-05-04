package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, UserRepositoryCustom {
}
