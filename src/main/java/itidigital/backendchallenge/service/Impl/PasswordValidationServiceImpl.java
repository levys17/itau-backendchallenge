package itidigital.backendchallenge.service.Impl;

import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;
import itidigital.backendchallenge.exceptionhandler.exceptions.RuleProcessingException;
import itidigital.backendchallenge.service.PasswordValidationService;
import itidigital.backendchallenge.service.validationrules.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Service
public class PasswordValidationServiceImpl implements PasswordValidationService {

    @Override
    public PasswordValidationResponseDTO validatePassword(String password) {
        log.info("c=PasswordValidationServiceImpl m=validatePassword status=start password:{}", password);
        try {
            PasswordValidationRules rules =
                    new PasswordLengthRule(
                            new NoWhitespaceRule(
                                    new LowercaseLetterRule(
                                            new UppercaseLetterRule(
                                                    new OneDigitRule(
                                                            new SpecialCharRule(
                                                                    new NoRepeatedCharRule(
                                                                            new ValidPassword())))))));

            return new PasswordValidationResponseDTO(rules.isValid(password), Collections.emptyList());
        } catch (Exception ex) {
            log.error("c=PasswordValidationServiceImpl m=validatePassword status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }

    @Override
    public PasswordValidationResponseDTO validatePasswordWithDetails(String password) {
        try {
            log.info("c=PasswordValidationServiceImpl m=validatePasswordWithDetails status=start password:{}", password);
            PasswordValidationRules rules =
                    new PasswordLengthRule(
                            new NoWhitespaceRule(
                                    new LowercaseLetterRule(
                                            new UppercaseLetterRule(
                                                    new OneDigitRule(
                                                            new SpecialCharRule(
                                                                    new NoRepeatedCharRule(
                                                                            new ValidPassword())))))));

            return rules.isValidWithDetails(password, new ArrayList<>());
        } catch (Exception ex) {
            log.error("c=PasswordValidationServiceImpl m=validatePasswordWithDetails status=error password:{} cause:{}", password, ex.getMessage());
            throw new RuleProcessingException(ex.getMessage());
        }
    }
}
