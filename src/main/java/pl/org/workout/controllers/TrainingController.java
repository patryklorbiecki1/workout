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
import pl.org.workout.dtos.Response.TrainingResponse;
import pl.org.workout.services.TrainingService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/training")
public class TrainingController {
    TrainingService trainingService;

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("all")
    public List<TrainingResponse> getAll() {
        return trainingService.getAll();
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("{id}")
    public TrainingResponse get(@PathVariable String id) {
        return trainingService.get(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PostMapping("add_training")
    public TrainingResponse createTraining(@RequestBody AddTrainingRequest addTrainingRequest) {
        return trainingService.addTraining(addTrainingRequest);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PutMapping("update")
    public Optional<TrainingResponse> updateTraining(@RequestBody TrainingUpdateRequest request) {
        return trainingService.update(request);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> removeTraining(@PathVariable String id) {
        trainingService.remove(id);
        return new ResponseEntity<>("Training deleted", HttpStatus.GONE);
    }


}
