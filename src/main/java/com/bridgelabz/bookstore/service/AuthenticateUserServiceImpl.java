package com.bridgelabz.bookstore.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.config.Passwordconfig;
import com.bridgelabz.bookstore.dto.EmailDto;
import com.bridgelabz.bookstore.dto.Setpassworddto;
import com.bridgelabz.bookstore.exception.Forgotpasswordexception;
import com.bridgelabz.bookstore.exception.Tokenexception;
import com.bridgelabz.bookstore.model.ERole;
import com.bridgelabz.bookstore.model.Role;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.payload.response.JwtResponse;
import com.bridgelabz.bookstore.payload.response.MessageResponse;
import com.bridgelabz.bookstore.repository.RoleRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.RabbitMqUtilty;
import com.bridgelabz.bookstore.utility.SimpleMailUtility;
import com.bridgelabz.bookstore.utility.Tokenutility;

@Service
public class AuthenticateUserServiceImpl implements IAuthenticateUserService {
	
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
    private Tokenutility tokenutility;

    @Autowired
    private SimpleMailUtility simpleMailUtility;

    @Autowired
    private IBookService bookService;

    @Autowired
    private JavaMailSenderImpl javaMailSender; // use JavaMailSender class

    @Autowired
    private RabbitMqUtilty rabbitMqUtilty;

    @Autowired
    private EmailDto rabbitMqDto;
    
    
    
    @Autowired
    private RabbitTemplate template;
    
    @Autowired
    private Passwordconfig passwordconfig;

   @Override
    public ResponseEntity authenticateUser(LoginRequest loginRequest) {

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

   
   @Override
    public ResponseEntity registerUser( SignupRequest signUpRequest) {

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
        rabbitMqDto.setBody("click this link to verify your account "+ "http://localhost:8093/api/auth/verifyuser/" + user.getId());
        rabbitMqUtilty.sendMessageToQueue(rabbitMqDto);
        //javaMailSender.send(simpleMailUtility.verifyUserMail(signUpRequest.getEmail(),  " http://localhost:9036/verifyuser/"+user.getId()));
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

@Override
public Response findEmail(String email) {
	
	User user = userRepository.findByEmail(email);   // find by user email id
	if (user == null) {								// if user email id it null response to user not register it
		throw new Forgotpasswordexception(MessageReference.USER_NOT_EXISTING);
	}else {
		
		String token = tokenutility.createToken(user.getId());
		EmailDto rabbitMqDto = RabbitMqUtilty.getRabbitMq(email, token);
		template.convertAndSend("userMessageQueue", rabbitMqDto);
		javaMailSender.send(RabbitMqUtilty.verifyUserMail(email, token, MessageReference.Verfiy_MAIL_TEXT+user.getId())); // send email
		return new Response(400, "user  email found", token);
		}	
	}


@Override
public Response setPassword(Setpassworddto setpassworddto, String token) {
	
	System.out.println("1");
	System.out.println(token);
	long userId = tokenutility.getUserIdFromToken(token);
	System.out.println("2"+userId);
	String email = userRepository.findById(userId).get().getEmail(); // find user email present or not
	User updatedUser = userRepository.findByEmail(email);
	System.out.println("4");
	System.out.println(updatedUser);
	
	if(setpassworddto.getPassword().equals(setpassworddto.getCfmpassword())) {
		
		System.out.println(setpassworddto);
		updatedUser.setPassword(passwordconfig.encoder().encode(setpassworddto.getPassword()));
		updateuserByEmail(updatedUser, email);
		return new Response(200, MessageReference.PASSWORD_CHANGE_SUCCESSFULLY, true);
	} else {
		System.out.println("3");
		return new Response(200, MessageReference.PASSWORD_IS_NOT_MATCHING, true);
	}
	
}


public String updateuserByEmail(User user, String email) {
	User updatedUser = userRepository.findByEmail(email);
	updatedUser = user;
	userRepository.save(updatedUser);
	return MessageReference.USER_UPDATE_SUCCESSFULLY;
}

public Response valivateUser(String token) {

	long userid = tokenutility.getUserIdFromToken(token); // get user id from user token.
	if (userid == 0) {
		throw new Tokenexception(MessageReference.INVALID_TOKEN);
	}
	User user = userRepository.findById(userid).get(); // check userid present or not
	if (user != null) { // if userid is found validate should be true
		user.setVerified(true);
		userRepository.save(user);
		return new Response(200, MessageReference.EMAIL_VERFIY, true);
	} else {
		return new Response(200,  MessageReference.NOT_VERFIY_EMAIL, false);

	}

}

}
