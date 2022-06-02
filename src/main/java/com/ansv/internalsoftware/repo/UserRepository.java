package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.UserEntity;
import com.ansv.internalsoftware.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {

    UserEntity findUserByUsername(String username);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long Id);

    @Query(value = "SELECT u.* FROM user as u LEFT JOIN department_user as du ON u.id = du.user_id WHERE du.department_id = :id", nativeQuery = true)
    List<UserEntity> findUserByDepartmentId(@Param("id") Long id);

    @Query(value = "SELECT u.* FROM user as u LEFT JOIN department_user as du ON u.id = du.user_id WHERE du.department_id = :id", nativeQuery = true)
    Integer deleteById(@Param("id") Long id);


}
