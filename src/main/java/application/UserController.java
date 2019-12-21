package application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController    
public class UserController {
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(path="/users/signup", consumes={"application/json"}, produces= {"application/json"}) // Map ONLY POST Requests
	public ResponseEntity<Response> addNewUser (@RequestBody User user) {
		System.out.println(passwordEncoder.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		JwtUser userDetails = jwtUserDetailsService.loadUserByUsername(user.getEmail());
		String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new TokenResponse(jwt));
	}
	
	@PostMapping(path = "/users/signin", consumes= {"application/json"},  produces= {"application/json"})
	public ResponseEntity<Response> signin(@RequestBody SigninRequest signinRequest) {
		Optional<User> userOption = userRepository.findByEmail(signinRequest.getEmail());
		if (!userOption.isPresent()) {
			return ResponseEntity.badRequest().body(new Response("Invalid credentials"));
		}
		User user = userOption.get();
		if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
			return ResponseEntity.badRequest().body(new Response("Invalid credentials"));
		}
		JwtUser userDetails = jwtUserDetailsService.loadUserByUsername(signinRequest.getEmail());
		String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new TokenResponse(jwt));
	}
	
	@DeleteMapping(path="users/delete/{ID}") // Map ONLY Delete Requests
	public String deleteUser (@PathVariable Long ID) {
		System.out.println(ID);
		Optional<User> user = userRepository.findById(ID);
		//Add try/catch blocks for null exception
		userRepository.delete(user.get());
		return "User with ID " + Long.toString(ID) + " deleted successfully";
	}

	@GetMapping(path="/all")
	public Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@GetMapping(path="/")
	public String sayHello() {
		return "Hello";
	}
}