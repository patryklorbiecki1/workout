package pl.org.workout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.org.workout.controllers.ExcerciseController;
import pl.org.workout.dtos.Response.ExcerciseResponse;
import pl.org.workout.security.JwtTokenUtil;
import pl.org.workout.services.ExcerciseService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExcerciseController.class)
public class ExcerciseControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ExcerciseService excerciseService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PasswordEncoder encoder;

    @Test
    @WithMockUser(username = "User")
    public void givenExcercise_whenGetExcerciseById_thenReturnExcercise() throws Exception{
        String excerciseId = "id";
        ExcerciseResponse excerciseResponse = new ExcerciseResponse("id","pull up",12,0.0,5);
        given(excerciseService.getExcerciseById(excerciseId)).willReturn(excerciseResponse);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/excercise/{id}",excerciseId));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(excerciseResponse.getId())))
                .andExpect(jsonPath("$.name",is(excerciseResponse.getName())))
                .andExpect(jsonPath("$.weight",is(excerciseResponse.getWeight())))
                .andExpect(jsonPath("$.reps",is(excerciseResponse.getReps())))
                .andExpect(jsonPath("$.sets",is(excerciseResponse.getSets())));
    }
}
