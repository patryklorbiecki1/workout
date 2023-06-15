package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Request.LoginRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.services.UserService;

import java.util.List;

@RestController
@CrossOrigin
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/user")
public class UserController {
    UserService userService;

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.signIn(loginRequest);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("all")
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("{username}")
    public UserResponse get(@PathVariable String username) {
        return userService.get(username);
    }

    @PostMapping("add_user")
    public UserResponse createUser(@RequestBody AddUserRequest addUserRequest) {
        return userService.addUser(addUserRequest);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public MessageResponse deleteUser(@PathVariable String id) {
        userService.remove(id);
        return MessageResponse.builder().message("User deleted").build();
    }
}
