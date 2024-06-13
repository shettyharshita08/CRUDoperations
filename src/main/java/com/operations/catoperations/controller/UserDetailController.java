package com.operations.catoperations.controller;

import com.operations.catoperations.dto.AuthRequestDTO;
import com.operations.catoperations.dto.ResponseDTO;
import com.operations.catoperations.dto.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/operations")
@Tag(name = "User Details Operations", description = "To Perform User Operations (Register , Login, Get)")
public interface UserDetailController {

    @Operation(summary = "Post Operation which Registers User", description = "Used to add a new User")
    @PostMapping("/register-user-details")
    ResponseDTO registerUserDetails(@RequestBody UserInfoDTO userInfoDTO);

    @Operation(summary = "Post Operation to Log In User", description = "Used to add Authenticate User and Log in")
    @PostMapping("/login-user-details")
    ResponseDTO loginUserDetails(@RequestBody AuthRequestDTO authRequestDTO);

    @Operation(summary = "Get Operation to get all users", description = "Used to fetch all users")
    @GetMapping("/getAllUsers")
    ResponseDTO getUser();

}
