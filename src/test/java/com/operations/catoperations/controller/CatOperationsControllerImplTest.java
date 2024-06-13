package com.operations.catoperations.controller;

import com.operations.catoperations.dto.ResponseDTO;
import com.operations.catoperations.service.CatOperationsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CatOperationsControllerImplTest {

    @Mock
    private CatOperationsService catOperationsService;

    @InjectMocks
    private CatOperationsControllerImpl catOperationsController;

    @Test
    public void addCatTest() {
        openMocks(this);

        MultipartFile file = new MockMultipartFile("file", "hello.txt", "text/plain", "Hello, World!".getBytes());

        ResponseDTO responseDTO = catOperationsController.addCat(file);

        verify(catOperationsService).addCatDetials(file);
    }

    @Test
    public void getCatTest() {
        openMocks(this);

        when(catOperationsService.getCatDetails()).thenReturn(Collections.emptyList());

        ResponseDTO responseDTO = catOperationsController.getCat();

        verify(catOperationsService).getCatDetails();
    }

    @Test
    public void getCatByIdTest() {
        openMocks(this);

        Integer id = 1;

        ResponseDTO responseDTO = catOperationsController.getCatById(id);

        verify(catOperationsService).getCatById(id);
    }

    @Test
    public void deleteCatByIdTest() {
        openMocks(this);

        Integer id = 1;

        ResponseDTO responseDTO = catOperationsController.deleteCatById(id);

        verify(catOperationsService).deleteCatById(id);
    }

    @Test
    public void updateCatByIdTest() {
        openMocks(this);

        MultipartFile file = new MockMultipartFile("file", "hello.txt", "text/plain", "Hello, World!".getBytes());

        ResponseDTO responseDTO = catOperationsController.updateCatById(file);

        verify(catOperationsService).updateCatDetials(file);
    }
}