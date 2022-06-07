package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Contract;
import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.repo.custom.ContractRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>, ContractRepositoryCustom {

    Optional<Contract> findById(Long id);

    void deleteById(Long Id);

    @Query(value="DELETE FROM contract as c WHERE 1=1 AND c.id IN :listId", nativeQuery=true)
    Integer deleteAll(@Param("listId") List<Long> listId);

}
