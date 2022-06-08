package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Function;
import com.ansv.internalsoftware.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>, FunctionRepositoryCustom {

    Optional<Function> findById(Long id);

    @Query(value="DELETE FROM function as func WHERE 1=1 AND func.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);







}
