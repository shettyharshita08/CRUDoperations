package com.operations.catoperations.controller;

import com.operations.catoperations.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/catoperations")
@Tag(name = "Cat Operations", description = "To Perform Cat Operations (Add, Update, Delete, Get)")
public interface CatOperationController {

    @Operation(summary = "Post Operation on Cat Images", description = "Used to add a new Cat Image")
    @PostMapping("/addCat")
    ResponseDTO addCat(@RequestParam("image") MultipartFile file);

    @Operation(summary = "Get Operation on Cat Images", description = "Used to get all Cat Images")
    @GetMapping("/getCat")
    ResponseDTO getCat();

    @Operation(summary = "Get Operation on Cat Images By Image Id", description = "Used to get Cat Images By Given Image Id")
    @GetMapping("/getCatById")
    ResponseDTO getCatById(@RequestParam("id") Integer id);

    @Operation(summary = "Delete Operation on Cat Images By Image Id", description = "Used to delete Cat Images By Given Image Id")
    @DeleteMapping("/deleteCatById")
    ResponseDTO deleteCatById(@RequestParam("id") Integer id);

    @Operation(summary = "Update Operation on Cat Image", description = "Used to Update recently added Cat Image")
    @PutMapping("/updateCat")
    ResponseDTO updateCatById(@RequestParam("image") MultipartFile file);
}
