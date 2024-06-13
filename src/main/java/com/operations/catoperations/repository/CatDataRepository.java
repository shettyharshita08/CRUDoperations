package com.operations.catoperations.repository;

import com.operations.catoperations.model.CatDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatDataRepository extends JpaRepository<CatDataEntity, Integer> {

    @Query(value = "select * from cat_information where user_id = :user_Id", nativeQuery = true)
    List<CatDataEntity> findByGivenUserId(@Param("user_Id") Integer user_Id);

    @Query(value = "select * from cat_information where image_id = :given_Id", nativeQuery = true)
    CatDataEntity findByGivenImageId(@Param("given_Id") Integer given_Id);

    @Query(value = "select * from cat_information where user_id = :given_Id order by image_id desc limit 1", nativeQuery = true)
    CatDataEntity updateByGivenImageId(@Param("given_Id") Integer user_Id);
}
