package pl.org.workout.services;

import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.ProfileResponse;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    ProfileResponse updateInfo(ProfileUpdateRequest profileUpdateRequest);

    Optional<ProfileResponse> get(String email);

    List<ProfileResponse> getAll();
}
