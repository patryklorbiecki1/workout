package pl.org.workout.dtos.Response;

import java.util.List;


public record JwtResponse(String token, String type, String username, List<String> roles) {
}
