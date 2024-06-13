package com.operations.catoperations.service;

import com.operations.catoperations.dto.AuthRequestDTO;
import com.operations.catoperations.dto.UserInfoDTO;
import com.operations.catoperations.exceptions.AuthenticationException;
import com.operations.catoperations.model.UserInfoEntity;
import com.operations.catoperations.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfoEntity> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void registerUser(UserInfoDTO userInfoDTO) {
        logger.info("[registerUser] User Registration Started for username : {}", userInfoDTO.getName());
        try {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setName(userInfoDTO.getName());
            userInfoEntity.setEmail(userInfoDTO.getEmail());
            userInfoEntity.setRole(userInfoDTO.getRole());
            userInfoEntity.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
            userInfoRepository.save(userInfoEntity);
        } catch (Exception e) {
            logger.error("[registerUser] Exception occurred while registering user : {}", e.getMessage());
            throw new AuthenticationException("User Registration Failed , Enter a new username");
        }
    }

    public String authenticateUser(AuthRequestDTO authRequestDTO) {
        logger.info("[authenticateUser] User Authentication Started for username : {}", authRequestDTO.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        try {
            if (authenticate.isAuthenticated()) {
                doAuthenticate(authRequestDTO.getUsername(), authRequestDTO.getPassword());
                String token = jwtService.generateToken(authRequestDTO.getUsername());
                String username = userInfoService.loadUserByUsername(authRequestDTO.getUsername()).getUsername();
                return "Token : " + token + " Username : " + username;
            }
        } catch (Exception e) {
            logger.error("[authenticateUser] Exception occurred while authenticating user : {}", e.getMessage());
            throw new AuthenticationException("User Authentication Failed");
        }
        throw new AuthenticationException("User Authentication Failed");
    }

    public void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    public List<UserInfoEntity> getAllUser() {
        return userInfoRepository.findAll();
    }
}
