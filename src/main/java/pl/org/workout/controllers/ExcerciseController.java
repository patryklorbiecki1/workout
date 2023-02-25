package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Request.ExcerciseUpdateRequest;
import pl.org.workout.services.ExcerciseService;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("api/excercise")
public class ExcerciseController {

    ExcerciseService excerciseService;
    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        try {
            return ResponseEntity.ok(excerciseService.getExcerciseById(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody ExcerciseUpdateRequest request){
        try{
            return ResponseEntity.ok(excerciseService.update(request));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody AddExcerciseRequest request){
        try {
            return ResponseEntity.ok(excerciseService.add(request));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
