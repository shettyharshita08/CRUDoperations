package com.operations.catoperations.repository;

import com.operations.catoperations.model.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {

    Optional<UserInfoEntity> findByName(String username);
}
