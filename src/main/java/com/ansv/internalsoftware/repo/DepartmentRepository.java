package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.repo.custom.DepartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentRepositoryCustom {
}
