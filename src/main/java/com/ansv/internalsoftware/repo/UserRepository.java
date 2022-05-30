package com.ansv.internalsoftware.repo;

import com.ansv.internalsoftware.modal.UserEntity;
import com.ansv.internalsoftware.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {

    UserEntity findUserByUsername(String username);



}
