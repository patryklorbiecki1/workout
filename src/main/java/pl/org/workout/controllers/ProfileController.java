package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.services.ProfileService;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("api/profile")
public class ProfileController {
    ProfileService profileService;

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        try {
            return ResponseEntity.ok(profileService.get(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping
    public ResponseEntity<?> update(ProfileUpdateRequest request){
        try{
            return ResponseEntity.ok(profileService.updateInfo(request));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
