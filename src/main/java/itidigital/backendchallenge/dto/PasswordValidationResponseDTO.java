package itidigital.backendchallenge.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import itidigital.backendchallenge.dto.view.Views;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@ApiModel("Password Validation Response")
@Data
@Builder
@AllArgsConstructor
public class PasswordValidationResponseDTO {

    @JsonView(Views.NoDetails.class)
    private Boolean isValid;

    private List<String> details;
}
