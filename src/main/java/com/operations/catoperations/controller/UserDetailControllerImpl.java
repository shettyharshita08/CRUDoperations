package com.operations.catoperations.controller;

import com.operations.catoperations.dto.AuthRequestDTO;
import com.operations.catoperations.dto.MetaDTO;
import com.operations.catoperations.dto.ResponseDTO;
import com.operations.catoperations.dto.UserInfoDTO;
import com.operations.catoperations.exceptions.AuthenticationException;
import com.operations.catoperations.exceptions.SuccessCode;
import com.operations.catoperations.model.UserInfoEntity;
import com.operations.catoperations.service.JwtService;
import com.operations.catoperations.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailControllerImpl implements UserDetailController {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailControllerImpl.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseDTO registerUserDetails(UserInfoDTO userInfoDTO) {
        logger.info("[registerUserDetails] User Registration Started for username : {}", userInfoDTO.getName());
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            userInfoService.registerUser(userInfoDTO);
        } catch (Exception e) {
            logger.error("[registerUserDetails] Exception occurred while registering user : {}", e.getMessage());
            throw new AuthenticationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_1.toString(), SuccessCode.CO_SUCCESS_1.getMessage()));
        responseDTO.setData(userInfoDTO.getName());
        return responseDTO;
    }

    @Override
    public ResponseDTO loginUserDetails(AuthRequestDTO authRequestDTO) {
        logger.info("[loginUserDetails] User Login Started for username : {}", authRequestDTO.getUsername());
        ResponseDTO responseDTO = new ResponseDTO();
        String userTokenData = null;
        try {
            userTokenData = userInfoService.authenticateUser(authRequestDTO);
        } catch (Exception e) {
            logger.error("[loginUserDetails] Exception occurred while logging user : {}", e.getMessage());
            throw new AuthenticationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_2.toString(), SuccessCode.CO_SUCCESS_2.getMessage()));
        responseDTO.setData(userTokenData);
        return responseDTO;
    }


    @Override
    public ResponseDTO getUser() {
        logger.info("[getUser] Get All User Details");
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserInfoEntity> userInfoEntities;
        try {
            userInfoEntities = userInfoService.getAllUser();
        } catch (Exception e) {
            logger.error("[getUser] Exception occurred while getting user : {}", e.getMessage());
            throw new AuthenticationException(e.getMessage());
        }
        responseDTO.setMeta(new MetaDTO(SuccessCode.CO_SUCCESS_3.toString(), SuccessCode.CO_SUCCESS_3.getMessage()));
        responseDTO.setData(userInfoEntities);
        return responseDTO;
    }
}
