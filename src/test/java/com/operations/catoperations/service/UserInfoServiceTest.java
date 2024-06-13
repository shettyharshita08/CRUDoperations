package com.operations.catoperations.service;

import com.operations.catoperations.dto.UserInfoDTO;
import com.operations.catoperations.model.UserInfoEntity;
import com.operations.catoperations.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserInfoServiceTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserInfoService userInfoService;

    @Test
    public void registerUserTest() {
        openMocks(this);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName("testUser");

        userInfoService.registerUser(userInfoDTO);

        verify(userInfoRepository).save(any(UserInfoEntity.class));
    }

    @Test
    public void doAuthenticateTest() {
        openMocks(this);

        userInfoService.doAuthenticate("testUser", "testPassword");

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void getAllUserTest() {
        openMocks(this);

        when(userInfoRepository.findAll()).thenReturn(Collections.emptyList());

        userInfoService.getAllUser();

        verify(userInfoRepository).findAll();
    }
}
