package itidigital.backendchallenge.service.validationrules;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class PasswordValidationRules {

    protected PasswordValidationRules nextRule;

    public abstract Boolean isValid(String password);

    public abstract PasswordValidationResponseDTO isValidWithDetails(String password, List<String> details);
}
