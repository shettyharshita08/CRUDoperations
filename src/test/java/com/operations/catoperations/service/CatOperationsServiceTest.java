package com.operations.catoperations.service;

import com.operations.catoperations.filter.JwtFilter;
import com.operations.catoperations.model.CatDataEntity;
import com.operations.catoperations.model.UserInfoEntity;
import com.operations.catoperations.repository.CatDataRepository;
import com.operations.catoperations.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class CatOperationsServiceTest {

    @Mock
    private JwtFilter jwtFilter;

    @Mock
    private CatDataRepository catDataRepository;

    @Mock
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    private CatOperationsService catOperationsService;

    @Test
    public void addCatDetailsTest() {
        openMocks(this);

        MultipartFile file = new MockMultipartFile("file", "hello.txt", "text/plain", "Hello, World!".getBytes());

        when(jwtFilter.extractUserName()).thenReturn("testUser");
        when(userInfoRepository.findByName("testUser")).thenReturn(Optional.of(new UserInfoEntity()));

        catOperationsService.addCatDetials(file);

        verify(catDataRepository).save(any(CatDataEntity.class));
    }

    @Test
    public void getCatByIdTest() {
        openMocks(this);

        Integer id = 1;

        when(catDataRepository.findByGivenImageId(id)).thenReturn(new CatDataEntity());

        catOperationsService.getCatById(id);

        verify(catDataRepository).findByGivenImageId(id);
    }

}