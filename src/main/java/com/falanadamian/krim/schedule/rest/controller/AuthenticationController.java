package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.UserDTO;
import com.falanadamian.krim.schedule.domain.mapper.RoleMapper;
import com.falanadamian.krim.schedule.domain.mapper.UserMapper;
import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.repository.RoleRepository;
import com.falanadamian.krim.schedule.security.jwt.JWTProvider;
import com.falanadamian.krim.schedule.security.jwt.JWTResponse;
import com.falanadamian.krim.schedule.security.model.SignInForm;
import com.falanadamian.krim.schedule.security.model.SignUpForm;
import com.falanadamian.krim.schedule.service.MailService;
import com.falanadamian.krim.schedule.service.UserService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("krim/authentication")
@CrossOrigin(origins = "*", exposedHeaders = "Authorization", maxAge = 3600)
public class AuthenticationController {

    private static final String API_URI = "/krim/authentication/";

    private AuthenticationManager authenticationManager;
    private JWTProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private MailService mailService;
    private UserMapper userMapper;

    public AuthenticationController(AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder, UserService userService, RoleRepository roleRepository, RoleMapper roleMapper, MailService mailService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.mailService = mailService;
        this.userMapper = userMapper;
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> signInUser(@Valid @RequestBody SignInForm signInForm) {

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Boolean rememberMe = (signInForm.isRememberMe() == null) ? false : signInForm.isRememberMe();
        String jwt = jwtProvider.generateJWT(authentication, rememberMe);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .body(new JWTResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpForm signUpForm) {
        if (userService.findOneByUsername(signUpForm.getUsername()).isPresent()) {
            throw new BadRequestException("Username: " + signUpForm.getUsername() + " already in use");
        }
        if (userService.findOneByEmailIgnoreCase(signUpForm.getEmail()).isPresent()) {
            throw new BadRequestException("Email: " + signUpForm.getEmail() + " already in use");
        }

        User user = userService.registerUser(signUpForm, signUpForm.getPassword());
        mailService.sendActivationEmail(user);
        mailService.sendAdminActivationEmail(user);

        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateSignUpHeader(user.getUsername()))
                .build();
    }

    @GetMapping("/activate")
    public ResponseEntity<UserDTO> activateUser(@RequestParam(value = "key") String key) {
        Optional<User> user = userService.activateRegistration(key);
        if (!user.isPresent())
            throw new BadRequestException("Invalid activation key");

        mailService.sendUserActivatedEmail(user.get());

        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateUserActivationSuccessfulHeader(user.get().getUsername()))
                .body(userMapper.toDto(user.get()));
    }

    @GetMapping("/password/reset")
    public void resetPassword(@RequestParam(value = "email") String email) {
        Optional<User> user = userService.requestPasswordReset(email);
        if (!user.isPresent())
            throw new BadRequestException("Invalid email adress");

        mailService.sendResetPasswordEmail(user.get());
    }

    @GetMapping("/password/set")
    public void setPassword(@RequestParam(value = "resetKey") String resetKey, @RequestParam(value = "password") String password) {
        Optional<User> user = userService.setPassword(password, resetKey);
        if (!user.isPresent())
            throw new BadRequestException("Invalid reset key!");

        mailService.sendResetPasswordCompletedEmail(user.get());
    }

    @GetMapping("/account")
    public UserDTO getAccount() {
        return userService.getUserWithRoles()
                .map(UserDTO::new)
                .orElseThrow(() -> new BadRequestException("User could not be found"));
    }

    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }

}
