package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.AddUserRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.UserResponse;
import pl.org.workout.services.UserService;

import java.util.List;

@RestController
@CrossOrigin
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/user")
public class UserController {
    UserService userService;
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
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

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.remove(id);
        return new ResponseEntity<>("User deleted",HttpStatus.GONE);
    }
}
