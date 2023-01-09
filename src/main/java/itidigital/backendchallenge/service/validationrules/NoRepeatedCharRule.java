package itidigital.backendchallenge.service.validationrules;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.exceptionhandler.exceptions.RuleProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;

@Slf4j
public class NoRepeatedCharRule extends PasswordValidationRules {

    private static final String DETAIL_MESSAGE = "Password must not contains repeated characters";

    public NoRepeatedCharRule(PasswordValidationRules nextRule) {
        super(nextRule);
    }

    @Override
    public Boolean isValid(String password) {
        try {
            log.info("c=NoRepeatedCharRule m=isValid status=start password:{}", password);
            HashSet<Character> uniqueChars = new HashSet<>();
            for (char i : password.toCharArray()) {
                if (uniqueChars.contains(i)) return false;
                else uniqueChars.add(i);
            }
            return nextRule.isValid(password);
        } catch (Exception ex) {
            log.error("c=NoRepeatedCharRule m=isValid status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }

    @Override
    public PasswordValidationResponseDTO isValidWithDetails(String password, List<String> details) {
        try {
            log.info("c=NoRepeatedCharRule m=isValidWithDetails status=start password:{}", password);
            HashSet<Character> uniqueChars = new HashSet<>();
            for (char i : password.toCharArray()) {
                if (uniqueChars.contains(i)) {
                    details.add(DETAIL_MESSAGE);
                    return nextRule.isValidWithDetails(password, details);
                } else uniqueChars.add(i);
            }
            return nextRule.isValidWithDetails(password, details);
        } catch (Exception ex) {
            log.error("c=NoRepeatedCharRule m=isValidWithDetails status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }
}
