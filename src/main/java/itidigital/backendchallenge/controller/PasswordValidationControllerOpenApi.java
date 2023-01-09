package itidigital.backendchallenge.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import itidigital.backendchallenge.dto.PasswordInputDTO;
import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@Tag(name = "Password Resource")
public interface PasswordValidationControllerOpenApi {

    @Operation(summary = "Endpoint to validate passwords", tags = "Password Resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PasswordValidationResponseDTO.class), examples = {
                            @ExampleObject(value = "{\n" +
                                    "    \"isValid\": true\n" +
                                    "}")
                    })
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PasswordValidationResponseDTO.class), examples = {
                            @ExampleObject(value = "{\n" +
                                    "    \"isValid\": false\n" +
                                    "}")
                    })
            })
    })
    ResponseEntity<PasswordValidationResponseDTO> validatePassword(
            @Parameter(name = "Password Input", description = "Password Input", required = true)
            PasswordInputDTO passwordInputDTO
    );


    @Operation(summary = "Endpoint to validate passwords with validations errors details", tags = "Password Resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PasswordValidationResponseDTO.class), examples = {
                            @ExampleObject(value = "{\n" +
                                    "    \"isValid\": true,\n" +
                                    "    \"details\": []\n" +
                                    "}")
                    })
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PasswordValidationResponseDTO.class), examples = {
                            @ExampleObject(value = "{\n" +
                                    "    \"isValid\": false,\n" +
                                    "    \"details\": [\n" +
                                    "        \"Password must not contains repeated characters\"\n" +
                                    "    ]\n" +
                                    "}")
                    })
            })
    })
    ResponseEntity<PasswordValidationResponseDTO> validatePasswordWithDetails(
            @Parameter(name = "Password Input", description = "Password Input", required = true)
            PasswordInputDTO passwordInputDTO
    );
}
