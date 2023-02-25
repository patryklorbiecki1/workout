package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.services.ProfileService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("api/profile")
public class ProfileController {
    ProfileService profileService;
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("{email}")
    public ResponseEntity<?> get(@PathVariable String email){
        try {
            return ResponseEntity.ok(profileService.get(email));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody ProfileUpdateRequest request){
        try{
            return ResponseEntity.ok(profileService.updateInfo(request));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("all")
    public ResponseEntity<List<ProfileResponse>> getAll(){
        return new ResponseEntity<>(profileService.getAll(),HttpStatus.OK);
    }
}
