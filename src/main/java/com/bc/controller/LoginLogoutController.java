package com.bc.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.Securityservices.UserDetailsImpl;
import com.bc.exception.LoginException;
import com.bc.login.security.jwt.JwtUtils;
import com.bc.model.Cart;
import com.bc.model.Customer;
import com.bc.model.ERole;
import com.bc.model.LoginDTO;
import com.bc.model.Role;
import com.bc.model.Wallet;
import com.bc.payload.request.LoginRequest;
import com.bc.payload.request.SignupRequest;
import com.bc.payload.response.MessageResponse;
import com.bc.payload.response.UserInfoResponse;
import com.bc.repository.CartRepo;
import com.bc.repository.CustomerRepo;
import com.bc.repository.RoleRepository;
import com.bc.service.LoginService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginLogoutController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	CartRepo cartRepo;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (customerRepo.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (customerRepo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Customer user = new Customer(signUpRequest.getUsername(),signUpRequest.getMobile(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),signUpRequest.getAddress());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Optional<Role> modRole = roleRepository.findByName(ERole.ROLE_USER);

			if(modRole.isEmpty()) {
				Role r = new Role();
				r.setName(ERole.ROLE_USER);
				roleRepository.save(r);
				roles.add(r);
			}else {
				roles.add(modRole.get());
			}
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":

					Optional<Role> modRole1 = roleRepository.findByName(ERole.ROLE_ADMIN);

					if(modRole1.isEmpty()) {
						Role r = new Role();
						r.setName(ERole.ROLE_ADMIN);
						roleRepository.save(r);
						roles.add(r);
					}else {
						roles.add(modRole1.get());
					}


					break;
				case "mod":
					Optional<Role> modRole2 = roleRepository.findByName(ERole.ROLE_MODERATOR);

					if(modRole2.isEmpty()) {
						Role r = new Role();
						r.setName(ERole.ROLE_MODERATOR);
						roleRepository.save(r);
						roles.add(r);
					}else {
						roles.add(modRole2.get());
					}



					break;
				default:
					Optional<Role> modRole3 = roleRepository.findByName(ERole.ROLE_USER);

					if(modRole3.isEmpty()) {
						Role r = new Role();
						r.setName(ERole.ROLE_USER);
						roleRepository.save(r);
						roles.add(r);
					}else {
						roles.add(modRole3.get());
					}
				}
			});
		}

		user.setRoles(roles);
		
		Cart cart = new Cart();
		Wallet w = new Wallet();
		user.setWallet(w);
		
		user.setCart(cart);
		cart.setCustomer(user);
		
		
		
		customerRepo.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signout")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
}