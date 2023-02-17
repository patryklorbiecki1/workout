package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.JwtResponse;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.security.JwtTokenUtil;
import pl.org.workout.services.UserService;

import java.util.List;

@RestController
@CrossOrigin
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/user")
public class UserController {
    UserService userService;
    AuthenticationManager authenticationManager;
    JwtTokenUtil jwtTokenUtil;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginForm) {
        // TODO add logic to userservice
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return ResponseEntity.badRequest().body("The user is already authenticated.");
//        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), loginForm.getPassword());
        Authentication authenticationResult = null;
        try {
            authenticationResult = authenticationManager.authenticate(authToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Wrong username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        UserDetails userDetails = (UserDetails) authenticationResult.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String jwtToken = jwtTokenUtil.generateJwtToken(authenticationResult);
        return ResponseEntity.ok(new JwtResponse(jwtToken,userDetails.getUsername(),roles));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("{username}")
    public ResponseEntity<?> get(@PathVariable String username){
        try {
            return ResponseEntity.ok(userService.get(username));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("add_user")
    public ResponseEntity<MessageResponse> createUser(@RequestBody AddUserRequest addUserRequest){
         return new ResponseEntity<>(userService.addUser(addUserRequest),HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.remove(id);
        return new ResponseEntity<>("User deleted",HttpStatus.GONE);
    }
}
