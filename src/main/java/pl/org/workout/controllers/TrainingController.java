package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.AddTrainingRequest;
import pl.org.workout.dtos.Request.TrainingUpdateRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.services.TrainingService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("api/training")
public class TrainingController {
    TrainingService trainingService;
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("all")
    public ResponseEntity<List<TrainingResponse>> getAll(){
        return new ResponseEntity<>(trainingService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        try {
            return ResponseEntity.ok(trainingService.get(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PostMapping("add_training")
    public ResponseEntity<MessageResponse> createTraining(@RequestBody AddTrainingRequest addTrainingRequest){
        return new ResponseEntity<>(trainingService.addTraining(addTrainingRequest),HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PutMapping("update")
    public ResponseEntity<?> updateTraining(@RequestBody TrainingUpdateRequest request) {
        try {
            return new ResponseEntity<>(trainingService.update(request), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> removeTraining(@PathVariable String id){
         trainingService.remove(id);
         return new ResponseEntity<>("Training deleted",HttpStatus.GONE);
    }


}
