package com.operations.catoperations.service;

import com.operations.catoperations.controller.UserDetailControllerImpl;
import com.operations.catoperations.exceptions.ApplicationException;
import com.operations.catoperations.filter.JwtFilter;
import com.operations.catoperations.model.CatDataEntity;
import com.operations.catoperations.model.UserInfoEntity;
import com.operations.catoperations.repository.CatDataRepository;
import com.operations.catoperations.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
public class CatOperationsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailControllerImpl.class);

    String FOLDER_PATH = "/Users/shreyasmante/Downloads/CatImages/";
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CatDataRepository catDataRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public void addCatDetials(MultipartFile file) {
        String userName = jwtFilter.extractUserName();
        UserInfoEntity userInfoEntity = userInfoRepository.findByName(userName).get();
        logger.info("[addCatDetials] Cat Addition Started for user : {}", userName);
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        try {
            CatDataEntity catDataEntity = new CatDataEntity();
            catDataEntity.setFilename(file.getOriginalFilename());
            catDataEntity.setType(file.getContentType());
            catDataEntity.setFilepath(filePath);
            catDataEntity.setUser_id(userInfoEntity);
            catDataRepository.save(catDataEntity);
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            logger.error("[addCatDetials] Exception occurred while adding cat : {}", e.getMessage());
            throw new ApplicationException("Exception occurred while adding cat : " + e.getMessage());
        }
    }

    public List<CatDataEntity> getCatDetails() {
        String userName = jwtFilter.extractUserName();
        UserInfoEntity userInfoEntity = userInfoRepository.findByName(userName).get();
        List<CatDataEntity> catDataEntityList;
        logger.info("[getCatDetails] Getting Cat Started for user : {}", userName);
        try {
            catDataEntityList = catDataRepository.findByGivenUserId(userInfoEntity.getId());
            if (catDataEntityList == null) {
                throw new ApplicationException("No cat Images found for given user");
            }
        } catch (Exception e) {
            logger.error("[getCatDetails] Exception occurred while getting cat : {}", e.getMessage());
            throw new ApplicationException("Exception occurred while getting cat : " + e.getMessage());
        }
        return catDataEntityList;
    }

    public CatDataEntity getCatById(Integer id) {
        logger.info("[getCatDetials] Getting Cat Started for id : {}", id);
        CatDataEntity catDataEntity;
        try {
            catDataEntity = catDataRepository.findByGivenImageId(id);
            if (catDataEntity == null) {
                throw new ApplicationException("No cat found with given id");
            }
        } catch (Exception e) {
            logger.error("[getCatDetials] Exception occurred while getting cat : {}", e.getMessage());
            throw new ApplicationException("Exception occurred while getting cat : " + e.getMessage());
        }
        return catDataEntity;
    }

    public void deleteCatById(Integer id) {
        logger.info("[deleteCatById] Delete cat started for id : {}", id);
        try {
            CatDataEntity catDataEntity = catDataRepository.findByGivenImageId(id);
            if (catDataEntity == null) {
                throw new ApplicationException("No cat found with given id to delete");
            }
            catDataRepository.delete(catDataEntity);
        } catch (Exception e) {
            logger.error("[deleteCatById] Exception occurred while deleting cat : {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public void updateCatDetials(MultipartFile file) {
        logger.info("[addCatDetials] Updating cat Started for user");
        String userName = jwtFilter.extractUserName();
        UserInfoEntity userInfoEntity = userInfoRepository.findByName(userName).get();
        CatDataEntity catDataEntity;
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        try {
            catDataEntity = catDataRepository.updateByGivenImageId(userInfoEntity.getId());
            catDataEntity.setFilename(file.getOriginalFilename());
            catDataEntity.setType(file.getContentType());
            catDataEntity.setFilepath(filePath);
            catDataEntity.setUser_id(userInfoEntity);
            catDataRepository.save(catDataEntity);
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            logger.error("[addCatDetials] Exception occurred while adding cat : {}", e.getMessage());
            throw new ApplicationException("Exception occurred while adding cat : " + e.getMessage());
        }
    }
}
