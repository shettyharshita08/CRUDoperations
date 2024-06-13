package com.operations.catoperations.controller;

import com.operations.catoperations.dto.AuthRequestDTO;
import com.operations.catoperations.dto.UserInfoDTO;
import com.operations.catoperations.service.JwtService;
import com.operations.catoperations.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserDetailControllerImplTest {

    @Mock
    private UserInfoService userInfoService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserDetailControllerImpl userDetailController;

    @Test
    public void registerUserDetailsTest() {
        openMocks(this);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName("testUser");

        userDetailController.registerUserDetails(userInfoDTO);

        verify(userInfoService).registerUser(userInfoDTO);
    }

    @Test
    public void loginUserDetailsTest() {
        openMocks(this);

        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("testUser");
        authRequestDTO.setPassword("testPassword");

        userDetailController.loginUserDetails(authRequestDTO);

        verify(userInfoService).authenticateUser(authRequestDTO);
    }

    @Test
    public void getUserTest() {
        openMocks(this);

        userDetailController.getUser();

        verify(userInfoService).getAllUser();
    }
}