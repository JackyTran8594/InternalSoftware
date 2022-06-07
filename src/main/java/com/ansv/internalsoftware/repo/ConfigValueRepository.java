package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.model.Config;
import com.ansv.internalsoftware.model.ConfigValue;
import com.ansv.internalsoftware.repo.custom.ConfigRepositoryCustom;
import com.ansv.internalsoftware.repo.custom.ConfigValueRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConfigValueRepository extends JpaRepository<ConfigValue, Long>, ConfigValueRepositoryCustom {

    Optional<ConfigValue> findById(Long id);

    void deleteById(Long listId);

    @Query(value="DELETE FROM config as c WHERE 1=1 AND c.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);

    ConfigValue findByCode(String code);

}
