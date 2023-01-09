package itidigital.backendchallenge.service.validationrules;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.exceptionhandler.exceptions.RuleProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NoWhitespaceRule extends PasswordValidationRules {

    private static final String DETAIL_MESSAGE = "Password must not contains whitespace";

    public NoWhitespaceRule(PasswordValidationRules nextRule) {
        super(nextRule);
    }

    @Override
    public Boolean isValid(String password) {
        try {
            log.info("c=NoWhitespaceRule m=isValid status=start password:{}", password);
            for (char i : password.toCharArray()) {
                if (Character.isWhitespace(i)) {
                    return false;
                }
            }
            return nextRule.isValid(password);
        } catch (Exception ex) {
            log.error("c=NoWhitespaceRule m=isValid status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }

    @Override
    public PasswordValidationResponseDTO isValidWithDetails(String password, List<String> details) {
        try {
            log.info("c=NoWhitespaceRule m=isValidWithDetails status=start password:{}", password);
            for (char i : password.toCharArray()) {
                if (Character.isWhitespace(i)) {
                    details.add(DETAIL_MESSAGE);
                    return nextRule.isValidWithDetails(password, details);
                }
            }
            return nextRule.isValidWithDetails(password, details);
        } catch (Exception ex) {
            log.error("c=NoWhitespaceRule m=isValidWithDetails status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }
}
