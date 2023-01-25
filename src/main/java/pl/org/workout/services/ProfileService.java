package pl.org.workout.services;

import pl.org.workout.dtos.Response.MessageResponse;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.exceptions.EntityNotFoundException;

public interface ProfileService {

    MessageResponse updateInfo();
    ProfileResponse get(Long id) throws EntityNotFoundException;
}
