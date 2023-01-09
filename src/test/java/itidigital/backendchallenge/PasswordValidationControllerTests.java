package itidigital.backendchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import itidigital.backendchallenge.dto.PasswordInputDTO;
import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.exceptionhandler.ExceptionDetail;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordValidationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static final String URI = "/password/validate";
    private static final String DETAILS_URI = "/password/validate/details";

    @Test
    public void shouldReturnTrueForValidPassword() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .content(mapper.writeValueAsString(builderInputDTO("AbTp9!fok")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        PasswordValidationResponseDTO responseDTO = mapper.readValue(response.getResponse().getContentAsString(), PasswordValidationResponseDTO.class);
        assertTrue(responseDTO.getIsValid());
    }

    @Test
    public void shouldReturnFalseAndBadRequestForValidPassword() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .content(mapper.writeValueAsString(builderInputDTO("AAAbbbCc")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        PasswordValidationResponseDTO responseDTO = mapper.readValue(response.getResponse().getContentAsString(), PasswordValidationResponseDTO.class);
        assertFalse(responseDTO.getIsValid());
    }

    @Test
    public void shouldReturnTrueForValidPasswordAndEmptyDetails() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(DETAILS_URI)
                        .header("password", "AbTp9!fok")
                        .content(mapper.writeValueAsString(builderInputDTO("AbTp9!fok")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        PasswordValidationResponseDTO responseDTO = mapper.readValue(response.getResponse().getContentAsString(), PasswordValidationResponseDTO.class);
        assertTrue(responseDTO.getIsValid());
        assertEquals(0, responseDTO.getDetails().size());
    }

    @Test
    public void shouldReturnFalseAndBadRequestForValidPasswordAndDetails() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(DETAILS_URI)//
                        .content(mapper.writeValueAsString(builderInputDTO("AAAbbbCc")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        PasswordValidationResponseDTO responseDTO = mapper.readValue(response.getResponse().getContentAsString(), PasswordValidationResponseDTO.class);
        assertFalse(responseDTO.getIsValid());
        assertNotEquals(0, responseDTO.getDetails().size());
    }

    @Test
    public void shouldReturnBadRequestAndExceptionInfosForNullInputPassword() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .content(mapper.writeValueAsString(builderInputDTO(null)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        ExceptionDetail responseDTO = mapper.readValue(response.getResponse().getContentAsString(), ExceptionDetail.class);
        assertEquals("Invalid Input", responseDTO.getTitle());
        assertEquals("Data of one or more fields are invalid. Verify and retry.", responseDTO.getDetail());
        assertEquals("password is required.", responseDTO.getFields().stream().findAny().get().getUserMessage());
    }

    private PasswordInputDTO builderInputDTO(String password) {
        return PasswordInputDTO.builder().password(password).build();
    }
}
