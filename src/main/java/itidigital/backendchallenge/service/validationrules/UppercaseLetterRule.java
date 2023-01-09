package itidigital.backendchallenge.service.validationrules;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.exceptionhandler.exceptions.RuleProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class UppercaseLetterRule extends PasswordValidationRules{

    private static final String DETAIL_MESSAGE = "Password must contains at least one uppercase character";

    public UppercaseLetterRule(PasswordValidationRules nextRule) {
        super(nextRule);
    }

    @Override
    public Boolean isValid(String password) {
        try {
            log.info("c=UppercaseLetterRule m=isValid status=start password:{}", password);
            for (char i : password.toCharArray()){
                if (Character.isUpperCase(i)) return nextRule.isValid(password);
            }
            return false;
        } catch (Exception ex) {
            log.error("c=UppercaseLetterRule m=isValid status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }

    }

    @Override
    public PasswordValidationResponseDTO isValidWithDetails(String password, List<String> details) {
        try {
            log.info("c=UppercaseLetterRule m=isValidWithDetails status=start password:{}", password);
            for (char i : password.toCharArray()){
                if (Character.isUpperCase(i)) return nextRule.isValidWithDetails(password, details);
            }
            details.add(DETAIL_MESSAGE);
            return nextRule.isValidWithDetails(password, details);
        } catch (Exception ex) {
            log.error("c=UppercaseLetterRule m=isValidWithDetails status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }
}
