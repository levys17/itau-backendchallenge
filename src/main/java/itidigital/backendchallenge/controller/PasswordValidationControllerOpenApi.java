package itidigital.backendchallenge.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import itidigital.backendchallenge.dto.PasswordInputDTO;
import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.dto.view.Views;
import org.springframework.http.ResponseEntity;


@Api(tags = "Password Resource")
public interface PasswordValidationControllerOpenApi {

    @ApiOperation(value = "API", tags = "Password Resource", response = PasswordValidationResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid Password", response = PasswordValidationResponseDTO.class),
            @ApiResponse(code = 400, message = "Invalid Password", response = PasswordValidationResponseDTO.class)
    })
    @JsonView(Views.NoDetails.class)
    ResponseEntity<PasswordValidationResponseDTO> validatePassword(
            @ApiParam(name = "Password Input", value = "Password Input", required = true)
                    PasswordInputDTO passwordInputDTO
    );


    @ApiOperation(value = "Endpoint to validate passwords with validations errors details", tags = "Password Resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid Password", response = PasswordValidationResponseDTO.class),
            @ApiResponse(code = 400, message = "Invalid Password", response = PasswordValidationResponseDTO.class)
    })
    ResponseEntity<PasswordValidationResponseDTO> validatePasswordWithDetails(
            @ApiParam(name = "Password Input", value = "Password Input", required = true)
                    PasswordInputDTO passwordInputDTO
    );
}
