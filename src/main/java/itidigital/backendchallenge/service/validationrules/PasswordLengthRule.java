package itidigital.backendchallenge.service.validationrules;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.exceptionhandler.exceptions.RuleProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PasswordLengthRule extends PasswordValidationRules {

    private static final String DETAIL_MESSAGE = "Password must contains at least 9 characters";

    public PasswordLengthRule(PasswordValidationRules nextRule) {
        super(nextRule);
    }

    @Override
    public Boolean isValid(String password) {
        try {
            log.info("c=PasswordLengthRule m=isValid status=start password:{}", password);
            if (password.replaceAll(" ", "").length() >= 9) {
                return nextRule.isValid(password);
            }
            return false;
        } catch (Exception ex) {
            log.error("c=PasswordLengthRule m=isValid status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }

    @Override
    public PasswordValidationResponseDTO isValidWithDetails(String password, List<String> details) {
        try {
            log.info("c=PasswordLengthRule m=isValidWithDetails status=start password:{}", password);
            if (password.length() >= 9) {
                return nextRule.isValidWithDetails(password, details);
            }
            details.add(DETAIL_MESSAGE);
            return nextRule.isValidWithDetails(password, details);
        } catch (Exception ex) {
            log.error("c=PasswordLengthRule m=isValidWithDetails status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }


}
