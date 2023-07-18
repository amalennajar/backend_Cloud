package tn.esprit.healthcloud.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.config.JwtUtils;

import tn.esprit.healthcloud.config.request.LoginRequest;
import tn.esprit.healthcloud.config.request.ResetRequest;
import tn.esprit.healthcloud.config.response.JwtResponse;
import tn.esprit.healthcloud.config.response.MessageResponse;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.RoleRepository;
import tn.esprit.healthcloud.repositories.UserRepository;
import tn.esprit.healthcloud.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor

public class AuthController {

    AuthenticationManager authenticationManager;


    UserRepository userRepository;


    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    IUserService iUserService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!userDetails.getStatut()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Votre Compte est desactiver contacter un Admin"));
        }
        else {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails1 = (CustomUserDetails) authentication.getPrincipal();
        List<String> role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getFirstname(),
                        userDetails.getLastname(),
                        userDetails.getEmail(),
                        role.get(0),
                        userDetails.getStatut()));
    }}
    @DeleteMapping("/signout")
    public ResponseEntity<?> signoutUser(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve the JWT token from the request headers
        String jwt = jwtUtils.parseJwt(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));

        // Invalidate the JWT token by adding an expired token as a cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", "expired")
                .httpOnly(true)
                .maxAge(0)
                .secure(request.isSecure())
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new MessageResponse("Aurevoir!!"));
    }
    @PostMapping("/signup")
    public User registerUser(@Validated @RequestBody User user) {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        user.setStatut(true);

        userRepository.save(user);
        iUserService.addwithmail(user.getEmail());
        return user;
    }
    @PostMapping("/updatepassword")
    void updatePassword(@RequestBody ResetRequest resetRequest) {
        iUserService.updatePassword(resetRequest.getEmailUser(),resetRequest.getNewPassword(),resetRequest.getConfirmPassword());
    }

    @PostMapping("/sendme")
    public void forgotpass(@RequestBody ResetRequest resetRequest) {
        iUserService.forgotpass(resetRequest.getEmailUser());
    }
}

