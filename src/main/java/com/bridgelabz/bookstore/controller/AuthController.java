package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.RabbitMqDto;
import com.bridgelabz.bookstore.model.ERole;
import com.bridgelabz.bookstore.model.Role;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.payload.response.JwtResponse;
import com.bridgelabz.bookstore.payload.response.MessageResponse;
import com.bridgelabz.bookstore.repository.RoleRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.service.UserDetailsImpl;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.RabbitMqUtilty;
import com.bridgelabz.bookstore.utility.SimpleMailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //AuthenticationManager has a DaoAuthenticationProvider (with help of UserDetailsService & PasswordEncoder)
    // to validate UsernamePasswordAuthenticationToken object. If successful, AuthenticationManager returns a fully
    // populated Authentication object (including granted authorities).
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private SimpleMailUtility simpleMailUtility;

    @Autowired
    private IBookService bookService;

    @Autowired
    private JavaMailSenderImpl javaMailSender; // use JavaMailSender class

    @Autowired
    private RabbitMqUtilty rabbitMqUtilty;

    @Autowired
    private RabbitMqDto rabbitMqDto;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.get().isVerified()) {

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }else{
            return new ResponseEntity<>("please verify your account by visiting your email account to proceed", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole .ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        rabbitMqDto.setTo(user.getEmail());
        rabbitMqDto.setFrom("ganeshghodke783@gmail.com");
        rabbitMqDto.setSubject("Welcome to Book store, Thanks for registration");
        rabbitMqDto.setBody("click this link to verify your account "+ "http://localhost:8093/verifyuser/" + user.getId());
        rabbitMqUtilty.sendMessageToQueue(rabbitMqDto);
        //javaMailSender.send(simpleMailUtility.verifyUserMail(signUpRequest.getEmail(),  " http://localhost:8093/verifyuser/"+user.getId()));
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}