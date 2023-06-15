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
import pl.org.workout.dtos.Response.MessageResponse;
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
    public MessageResponse update(@RequestBody ExcerciseUpdateRequest request) {
        excerciseService.update(request);
        return MessageResponse.builder().message("User updated").build();
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PostMapping("add")
    public MessageResponse add(@RequestBody AddExcerciseRequest request) {
        excerciseService.add(request);
        return MessageResponse.builder().message("User created").build();
    }


}
