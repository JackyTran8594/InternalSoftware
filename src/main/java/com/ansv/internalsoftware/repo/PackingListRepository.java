package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.PackingList;
import com.ansv.internalsoftware.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackingListRepository extends JpaRepository<PackingList, Long>, PackingListRepositoryCustom {

    Optional<PackingList> findById(Long id);

    @Query(value="DELETE FROM packing_list as pl WHERE 1=1 AND pl.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);



}
