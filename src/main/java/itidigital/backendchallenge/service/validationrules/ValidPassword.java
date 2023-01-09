package itidigital.backendchallenge.service.validationrules;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import java.util.List;

public class ValidPassword extends PasswordValidationRules{

    public ValidPassword() {
        super(null);
    }

    @Override
    public Boolean isValid(String password) {
        return true;
    }

    @Override
    public PasswordValidationResponseDTO isValidWithDetails(String password, List<String> details) {
        if (details.isEmpty()) {
            return new PasswordValidationResponseDTO(true, details);
        } else {
            return new PasswordValidationResponseDTO(false, details);
        }
    }
}
