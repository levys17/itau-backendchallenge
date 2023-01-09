package itidigital.backendchallenge.controller;

import com.fasterxml.jackson.annotation.JsonView;
import itidigital.backendchallenge.dto.PasswordInputDTO;
import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.dto.view.Views;
import itidigital.backendchallenge.service.PasswordValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/password")
public class PasswordValidationController implements PasswordValidationControllerOpenApi {

    private final PasswordValidationService passwordValidationService;

    @Override
    @JsonView({Views.NoDetails.class})
    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordValidationResponseDTO> validatePassword(
            @RequestBody @Valid PasswordInputDTO passwordInputDTO
    ) {
         try{
             log.info("c=PasswordController m=validatePassword status=start password:{}", passwordInputDTO.getPassword());
             PasswordValidationResponseDTO response = passwordValidationService.validatePassword(passwordInputDTO.getPassword());
             return (response.getIsValid() ? ResponseEntity.ok().body(response) : ResponseEntity.badRequest().body(response));
        } catch (Exception ex){
             log.error("c=PasswordController m=validatePassword status=error password:{} cause:{}", passwordInputDTO.getPassword(), ex.getMessage());
             throw new RuntimeException(ex.getMessage());
         }
    }

    @Override
    @PostMapping(value = "/validate/details", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordValidationResponseDTO> validatePasswordWithDetails(
            @RequestBody @Valid PasswordInputDTO passwordInputDTO
    ) {
        try{
            log.info("c=PasswordController m=validatePasswordWithDetails status=start password:{}", passwordInputDTO.getPassword());
            PasswordValidationResponseDTO response = passwordValidationService.validatePasswordWithDetails(passwordInputDTO.getPassword());
            return (response.getIsValid() ? ResponseEntity.ok().body(response) : ResponseEntity.badRequest().body(response));
        } catch (Exception ex){
            log.error("c=PasswordController m=validatePasswordWithDetails status=error password:{} cause:{}", passwordInputDTO.getPassword(), ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
