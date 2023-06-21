package pl.org.workout.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.services.ProfileService;

import java.util.List;
import java.util.Optional;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/profile")
public class ProfileController {
    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("{email}")
    public Optional<ProfileResponse> get(@PathVariable String email) {
        return profileService.get(email);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @PatchMapping
    public ProfileResponse update(@RequestBody ProfileUpdateRequest request) {
        return profileService.updateInfo(request);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    @GetMapping("all")
    public List<ProfileResponse> getAll() {
        return profileService.getAll();
    }
}
