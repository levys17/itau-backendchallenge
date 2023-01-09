package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.NoWhitespaceRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoWhitespaceRuleTests {

    private static final String DETAIL_MESSAGE = "Password must not contains whitespace";

    @Test
    public void shouldReturnTrueForPasswordWithoutWhitespace() {
        assertTrue(new NoWhitespaceRule(new ValidPassword()).isValid("012345a6789"));
        assertTrue(new NoWhitespaceRule(new ValidPassword()).isValid("ABCDghi@123"));
        assertTrue(new NoWhitespaceRule(new ValidPassword()).isValid("1234@bcdefghi"));
    }

    @Test
    public void shouldReturnFalseForPasswordWithWhitespace() {
        assertFalse(new NoWhitespaceRule(new ValidPassword()).isValid("12345    6789"));
        assertFalse(new NoWhitespaceRule(new ValidPassword()).isValid(" ABCDE@123"));
        assertFalse(new NoWhitespaceRule(new ValidPassword()).isValid("1234ABCDE    "));
    }

    @Test
    public void shouldReturnTrueForPasswordWithoutWhitespaceAndEmptyDetails(){
        assertTrue(new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("012345a6789", new ArrayList<>()).getIsValid());
        assertEquals(0,new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("012345a6789", new ArrayList<>()).getDetails().size());

        assertTrue(new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("ABCDghi@123", new ArrayList<>()).getIsValid());
        assertEquals(0,new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("ABCDghi@123", new ArrayList<>()).getDetails().size());

        assertTrue(new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("1234@bcdefghi", new ArrayList<>()).getIsValid());
        assertEquals(0,new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("1234@bcdefghi", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordWithWhitespaceAndDetailMessage(){
        assertFalse(new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("12345    6789", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("12345    6789", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new NoWhitespaceRule(new ValidPassword()).isValidWithDetails(" ABCDE@123", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoWhitespaceRule(new ValidPassword()).isValidWithDetails(" ABCDE@123", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("1234ABCDE    ", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoWhitespaceRule(new ValidPassword()).isValidWithDetails("1234ABCDE    ", new ArrayList<>()).getDetails().stream().findFirst().get());

    }
}
