package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConfigRepository extends JpaRepository<Config, Long>, ConfigRepositoryCustom {

    Optional<Config> findById(Long id);

    void deleteById(Long listId);

    @Query(value="DELETE FROM config as c WHERE 1=1 AND c.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);

    @Query(value = "SELECT c.* FROM config as c WHERE c.code = :code", nativeQuery = true)
    Config findByCode(@Param("code") String code);

}
