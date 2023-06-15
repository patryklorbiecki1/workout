package pl.org.workout.services;

import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.exceptions.EntityNotFoundException;

import java.util.List;

public interface ProfileService {

    ProfileResponse updateInfo(ProfileUpdateRequest profileUpdateRequest);
    ProfileResponse get(String email);
    List<ProfileResponse> getAll();
}
