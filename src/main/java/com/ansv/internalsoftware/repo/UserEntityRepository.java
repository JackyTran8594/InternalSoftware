package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, UserEntityRepositoryCustom {

    @Query(value = "SELECT u.* FROM user as u WHERE u.username = :username", nativeQuery = true)
    UserEntity findUserByUsername(@Param("username") String username);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long Id);

    @Query(value = "SELECT u.* FROM user as u LEFT JOIN department_user as du ON u.id = du.user_id WHERE du.department_id = :id", nativeQuery = true)
    List<UserEntity> findUserByDepartmentId(@Param("id") Long id);

    @Query(value = "SELECT u.* FROM user as u LEFT JOIN department_user as du ON u.id = du.user_id LEFT JOIN department as d ON d.id = du.department_id WHERE d.code = :code", nativeQuery = true)
    List<UserEntity> findByCode(@Param("code") String code);




}
