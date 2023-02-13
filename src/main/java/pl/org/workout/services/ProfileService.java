package pl.org.workout.services;

import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.exceptions.EntityNotFoundException;

public interface ProfileService {

    ProfileResponse updateInfo(ProfileUpdateRequest profileUpdateRequest) throws EntityNotFoundException;
    ProfileResponse get(String email) throws Exception;

}
