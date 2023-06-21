package pl.org.workout.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.org.workout.dtos.Request.ProfileUpdateRequest;
import pl.org.workout.dtos.Response.ProfileResponse;
import pl.org.workout.enitities.Profile;
import pl.org.workout.enitities.User;
import pl.org.workout.mapper.ProfileMapper;
import pl.org.workout.repositories.ProfileRepository;
import pl.org.workout.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;

    public ProfileServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileResponse updateInfo(ProfileUpdateRequest request) {
        Optional<User> user = userRepository.findUserByEmail(request.email());
        user.ifPresent(u -> {
            Profile profile = user.orElseThrow().getProfile();
            profileRepository.delete(profile);
            Optional.ofNullable(request.firstname()).ifPresent(profile::setFirstname);
            Optional.ofNullable(request.lastname()).ifPresent(profile::setLastname);
            Optional.ofNullable(request.weight()).ifPresent(profile::setWeight);
            Optional.ofNullable(request.height()).ifPresent(profile::setHeight);
            Optional.ofNullable(request.trainingGoal()).ifPresent(profile::setTrainingGoal);
            Optional.ofNullable(request.trainings()).ifPresent(profile::setTrainings);
            user.orElseThrow().setProfile(profile);
            profileRepository.save(profile);
            userRepository.save(u);
        });
        return user.map(u -> profileMapper.toProfileResponse(u.getProfile())).orElseThrow();
    }

    @Override
    public Optional<ProfileResponse> get(String email) {
        return userRepository.findUserByEmail(email).stream()
                .map(User::getProfile)
                .map(profileMapper::toProfileResponse)
                .findFirst();
    }

    @Override
    public List<ProfileResponse> getAll() {
        return profileRepository.findAll().stream().map(profileMapper::toProfileResponse)
                .collect(Collectors.toList());
    }
}
