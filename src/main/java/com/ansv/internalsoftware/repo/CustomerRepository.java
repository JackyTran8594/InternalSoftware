package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {

    Optional<Customer> findById(Long id);

    void deleteById(Long listId);

    @Query(value="DELETE FROM customer as c WHERE 1=1 AND c.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);
    @Query(value = "SELECT c.* FROM customer as c WHERE c.code = :code", nativeQuery = true)
    Customer findByCode(@Param("code") String code);

}
