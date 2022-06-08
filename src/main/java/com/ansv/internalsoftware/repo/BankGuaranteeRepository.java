package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.BankGuarantee;
import com.ansv.internalsoftware.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankGuaranteeRepository extends JpaRepository<BankGuarantee, Long>, BankGuaranteeRepositoryCustom {

    Optional<BankGuarantee> findById(Long id);

    @Query(value="DELETE FROM role as r WHERE 1=1 AND r.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);








}
