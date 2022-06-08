package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.PeriodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PeriodOrderRepository extends JpaRepository<PeriodOrder, Long>, PeriodOrderRepositoryCustom {

    Optional<PeriodOrder> findById(Long id);

    @Query(value="DELETE FROM perioid_order WHERE id IN :listId", nativeQuery=true)
    Integer deleteAll(@Param("listId") List<Long> listId);

    void deleteById(Long listId);

}
