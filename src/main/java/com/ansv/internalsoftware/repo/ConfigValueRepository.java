package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.ConfigValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConfigValueRepository extends JpaRepository<ConfigValue, Long>, ConfigValueRepositoryCustom {

//    Optional<ConfigValue> findById(Long id);
//    void deleteById(Long listId);
    @Query(value="DELETE FROM config as c WHERE 1=1 AND c.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);
//    @Query(value = "SELECT cv.* FROM config_value as cv WHERE cv.code = :code", nativeQuery = true)
//    ConfigValue findByCode(@Param("code") String code);
//    ConfigValue findByCode(String code);

}
