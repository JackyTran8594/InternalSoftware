package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.repo.custom.RoleRepositoryCustom;
import com.ansv.internalsoftware.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {

    Optional<Role> findById(Long id);

    @Query(value = "SELECT f.id, f.action_code FROM role_function AS rf, function AS f WHERE rf.role_id = f.id AND rf.role_id IN :listId", nativeQuery = true)
    List<String> getRoleWithList(@Param("listId") List<Long> listId);

    void deleteById(Long listId);

    Integer deleteAll(List<Long> listId);

    Role findByCode(String code);





}
