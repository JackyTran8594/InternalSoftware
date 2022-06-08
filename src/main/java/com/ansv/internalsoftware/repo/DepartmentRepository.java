package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentRepositoryCustom {

    Optional<Department> findById(Long id);

    @Query(value = "SELECT f.id, f.action_code FROM role_function AS rf, function AS f WHERE rf.role_id = f.id AND rf.role_id IN :listId", nativeQuery = true)
    List<String> getRoleWithList(@Param("listId") List<Long> listId);

//    void deleteById(Long listId);

    @Query(value="DELETE FROM department as d WHERE 1=1 AND d.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);
    @Query(value = "SELECT d.* FROM department as d WHERE d.code = :code", nativeQuery = true)
    Department findByCode(@Param("code") String code);

    @Query(value = "SELECT d.* FROM department AS d LEFT JOIN department_user AS du ON d.id = du.department_id LEFT JOIN [user] AS u ON r.id = u.role_id WHERE u.id = :userId", nativeQuery = true)
    List<Department> findDepartmentByUserId(@Param("userId") Long userId);
}
