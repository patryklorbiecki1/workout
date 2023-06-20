package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.AddExcerciseRequest;
import pl.org.workout.dtos.Request.ExcerciseUpdateRequest;
import pl.org.workout.dtos.Response.ExcerciseResponse;
import pl.org.workout.services.ExcerciseService;

import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/excercise")
public class ExcerciseController {

    ExcerciseService excerciseService;

    @GetMapping("{id}")
    public Optional<ExcerciseResponse> get(@PathVariable String id) {
        return excerciseService.getExcerciseById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PatchMapping
    public ExcerciseResponse update(@RequestBody ExcerciseUpdateRequest request) {
        return excerciseService.update(request);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PostMapping("add")
    public ExcerciseResponse add(@RequestBody AddExcerciseRequest request) {
        return excerciseService.add(request);
    }


}
