package com.kalyon.pvportalbackend.controller;

import com.kalyon.pvportalbackend.config.impl.UserDetailsImpl;
import com.kalyon.pvportalbackend.dto.request.LoginRequest;
import com.kalyon.pvportalbackend.dto.request.RegisterRequest;
import com.kalyon.pvportalbackend.dto.response.JWTResponse;
import com.kalyon.pvportalbackend.dto.response.MessageResponse;
import com.kalyon.pvportalbackend.exception.NotNullException;
import com.kalyon.pvportalbackend.exception.ResourceAlreadyExist;
import com.kalyon.pvportalbackend.model.EnumRole;
import com.kalyon.pvportalbackend.model.Role;
import com.kalyon.pvportalbackend.repository.RoleRepository;
import com.kalyon.pvportalbackend.repository.UserRepository;
import com.kalyon.pvportalbackend.util.JwtUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@Api("bu bir testtir")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Bu bir testtir");
    }



    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest);

        if (userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ResourceAlreadyExist("Username already exist");
        }else if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ResourceAlreadyExist("Email already registered");
        }

        if(registerRequest.getEmail()==null || registerRequest.getEmpCode()==null
            || registerRequest.getUserName()==null || registerRequest.getPassword()==null){
          throw new NotNullException("Mandatory fields can not be null");
        }
/*
        roleRepository.saveAll(Arrays.asList(new Role(EnumRole.ROLE_USER),
                                            new Role(EnumRole.ROLE_MANAGER),
                                            new Role(EnumRole.ROLE_ADMIN),
                                            new Role(EnumRole.ROLE_SUPERADMIN)));
*/

        registerRequest.setRoles(Set.of(roleRepository.findByName(EnumRole.ROLE_USER)));
        System.out.println(registerRequest);
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(registerRequest.mapToUsers());
        return ResponseEntity.ok(new MessageResponse(new Date(), "successful"));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = principal.getAuthorities()
                .stream().map(item-> item.getAuthority()).collect(Collectors.toList());


        return ResponseEntity.ok(new JWTResponse(token, principal.getUsername(),roles));

    }

}
