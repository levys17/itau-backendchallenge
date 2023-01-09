package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.OneDigitRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OneDigitRuleTests {

    private static final String DETAIL_MESSAGE = "Password must contains at least one digit";

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneDigit(){
        assertTrue(new OneDigitRule(new ValidPassword()).isValid("abcdef1GH@34"));
        assertTrue(new OneDigitRule(new ValidPassword()).isValid("1BCDghi@!@@"));
        assertTrue(new OneDigitRule(new ValidPassword()).isValid("abcDEfGHi@1"));
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutDigit(){
        assertFalse(new OneDigitRule(new ValidPassword()).isValid("abcdefghijk"));
        assertFalse(new OneDigitRule(new ValidPassword()).isValid("ABCDE@xyz"));
        assertFalse(new OneDigitRule(new ValidPassword()).isValid("@!@#ABCDE"));
    }

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneDigitAndEmptyDetails(){
        assertTrue(new OneDigitRule(new ValidPassword()).isValidWithDetails("abcdef1GH@34", new ArrayList<>()).getIsValid());
        assertEquals(0,new OneDigitRule(new ValidPassword()).isValidWithDetails("abcdef1GH@34", new ArrayList<>()).getDetails().size());

        assertTrue(new OneDigitRule(new ValidPassword()).isValidWithDetails("1BCDghi@!@@", new ArrayList<>()).getIsValid());
        assertEquals(0,new OneDigitRule(new ValidPassword()).isValidWithDetails("1BCDghi@!@@", new ArrayList<>()).getDetails().size());

        assertTrue(new OneDigitRule(new ValidPassword()).isValidWithDetails("abcDEfGHi@1", new ArrayList<>()).getIsValid());
        assertEquals(0,new OneDigitRule(new ValidPassword()).isValidWithDetails("abcDEfGHi@1", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutDigitAndDetailMessage(){
        assertFalse(new OneDigitRule(new ValidPassword()).isValidWithDetails("abcdefghijk", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new OneDigitRule(new ValidPassword()).isValidWithDetails("abcdefghijk", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new OneDigitRule(new ValidPassword()).isValidWithDetails("ABCDE@xyz", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new OneDigitRule(new ValidPassword()).isValidWithDetails("ABCDE@xyz", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new OneDigitRule(new ValidPassword()).isValidWithDetails("@!@#ABCDE", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new OneDigitRule(new ValidPassword()).isValidWithDetails("@!@#ABCDE", new ArrayList<>()).getDetails().stream().findFirst().get());

    }
}
