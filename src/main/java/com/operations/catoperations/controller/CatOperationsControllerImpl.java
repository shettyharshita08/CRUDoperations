package com.operations.catoperations.controller;

import com.operations.catoperations.dto.MetaDTO;
import com.operations.catoperations.dto.ResponseDTO;
import com.operations.catoperations.exceptions.ApplicationException;
import com.operations.catoperations.exceptions.SuccessCode;
import com.operations.catoperations.model.CatDataEntity;
import com.operations.catoperations.service.CatOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CatOperationsControllerImpl implements CatOperationController {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailControllerImpl.class);

    @Autowired
    private CatOperationsService catOperationsService;

    @Override
    public ResponseDTO addCat(MultipartFile file) {
        logger.info("[addCat] Cat Addition Started");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            catOperationsService.addCatDetials(file);
        } catch (Exception e) {
            logger.error("[addCat] Exception occurred while adding cat : {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_4.toString(), SuccessCode.CO_SUCCESS_4.getMessage()));
        return responseDTO;
    }

    @Override
    public ResponseDTO getCat() {
        logger.info("[getCat] Fetching Cats Started");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<CatDataEntity> catDataEntity = catOperationsService.getCatDetails();
            responseDTO.setData(catDataEntity);
        } catch (Exception e) {
            logger.error("[getCat] Exception occurred while getting cat : {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_4.toString(), SuccessCode.CO_SUCCESS_4.getMessage()));
        return responseDTO;
    }

    @Override
    public ResponseDTO getCatById(Integer id) {
        logger.info("[getCatById] Getting Cat by id : {}", id);
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            CatDataEntity catDataEntity = catOperationsService.getCatById(id);
            responseDTO.setData(catDataEntity);
        } catch (Exception e) {
            logger.error("[getCatById] Exception occurred while getting cat : {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_5.toString(), SuccessCode.CO_SUCCESS_5.getMessage()));
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteCatById(Integer id) {
        logger.info("[deleteCatById] Deleting Cat by id : {}", id);
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            catOperationsService.deleteCatById(id);
            responseDTO.setData("Cat Deleted Successfully");
        } catch (Exception e) {
            logger.error("[deleteCatById] Exception occurred while adding cat : {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_6.toString(), SuccessCode.CO_SUCCESS_6.getMessage()));
        return responseDTO;
    }

    @Override
    public ResponseDTO updateCatById(MultipartFile file) {
        logger.info("[addCat] Cat Addition Started");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            catOperationsService.updateCatDetials(file);
        } catch (Exception e) {
            logger.error("[addCat] Exception occurred while adding cat : {}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_4.toString(), SuccessCode.CO_SUCCESS_4.getMessage()));
        return responseDTO;
    }
}
