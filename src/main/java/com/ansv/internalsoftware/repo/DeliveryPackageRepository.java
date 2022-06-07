package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.DeliveryPackage;
import com.ansv.internalsoftware.repo.custom.DeliveryPackageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, Long>, DeliveryPackageRepositoryCustom {

    Optional<DeliveryPackage> findById(Long id);

//    List<DeliveryPackage> findAll();

    @Query(value="DELETE FROM delivery_package WHERE id IN :listId", nativeQuery=true)
    Integer deleteAll(@Param("listId") List<Long> listId);

//    void deleteById(Long listId);

}
