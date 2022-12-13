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
import pl.org.workout.exceptions.EntityNotFoundException;
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
    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.get(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("add_user")
    public ResponseEntity<MessageResponse> createUser(@RequestBody AddUserRequest addUserRequest){
        return new ResponseEntity<>(userService.addUser(addUserRequest),HttpStatus.CREATED);
    }

}
