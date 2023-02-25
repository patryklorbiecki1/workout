package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.enitities.Profile;
import pl.org.workout.enitities.User;
import pl.org.workout.exceptions.EntityNotFoundException;
import pl.org.workout.repositories.ProfileRepository;
import pl.org.workout.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProfileServiceImpl implements ProfileService{

    UserRepository userRepository;
    ProfileRepository profileRepository;
    @Override
    public ProfileResponse updateInfo(ProfileUpdateRequest request) throws EntityNotFoundException {
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(User.class));

            Profile profile = user.getProfile();
            if (request.getFirstname() != null) {
                profile.setFirstname(request.getFirstname());
            }
            if (request.getLastname() != null) {
                profile.setLastname(request.getLastname());
            }
            if (request.getWeight() != null) {
                profile.setWeight(request.getWeight());
            }
            if (request.getHeight() != null) {
                profile.setHeight(request.getHeight());
            }
            if (request.getTrainingGoal() != null) {
                profile.setTrainingGoal(request.getTrainingGoal());
            }
            if(request.getTrainings()!=null){
                profile.setTrainings(request.getTrainings());
            }
            profileRepository.deleteById(user.getProfile().getId());
            user.setProfile(profile);
            profileRepository.save(profile);
            return ProfileResponse.from(userRepository.save(user).getProfile());
    }
    @Override
    public ProfileResponse get(String email) throws EntityNotFoundException{
        return ProfileResponse.from(userRepository.findUserByEmail(email).orElseThrow().getProfile());
    }
    @Override
    public List<ProfileResponse> getAll() {
        return profileRepository.findAll().stream().map(ProfileResponse::from).collect(Collectors.toList());
    }
}
