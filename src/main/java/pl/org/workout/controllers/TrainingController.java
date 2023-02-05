package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.services.TrainingService;

import java.util.List;

@RestController
@CrossOrigin
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("api/training")
public class TrainingController {
    private final TrainingService trainingService;
    @Autowired
    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
    }
    @RequestMapping("all")
    public ResponseEntity<List<TrainingResponse>> getAll(){
        return new ResponseEntity<>(trainingService.getAll(), HttpStatus.OK);
    }
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        try {
            return ResponseEntity.ok(trainingService.get(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
