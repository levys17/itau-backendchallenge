package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.NoRepeatedCharRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NoRepeatedCharRuleTests {


    private static final String DETAIL_MESSAGE = "Password must not contains repeated characters";

    @Test
    public void shouldReturnTrueForPasswordWithoutRepeatedChar() {
        assertTrue(new NoRepeatedCharRule(new ValidPassword()).isValid("012345a6789"));
        assertTrue(new NoRepeatedCharRule(new ValidPassword()).isValid("ABCcDghi@123"));
        assertTrue(new NoRepeatedCharRule(new ValidPassword()).isValid("1234@bcdefghi"));
    }

    @Test
    public void shouldReturnFalseForPasswordWithRepeatedChar() {
        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValid("1123456789"));
        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValid("ABCDE@@123"));
        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValid("1234AABCDE"));
        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValid("1234aaBCDE"));
    }

    @Test
    public void shouldReturnTrueForPasswordWithoutRepeatedCharAndEmptyDetails(){
        assertTrue(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("012345a6789", new ArrayList<>()).getIsValid());
        assertEquals(0,new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("012345a6789", new ArrayList<>()).getDetails().size());

        assertTrue(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("ABCcDghi@123", new ArrayList<>()).getIsValid());
        assertEquals(0,new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("ABCcDghi@123", new ArrayList<>()).getDetails().size());

        assertTrue(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1234@bcdefghi", new ArrayList<>()).getIsValid());
        assertEquals(0,new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1234@bcdefghi", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordWithRepeatedCharAndDetailMessage(){
        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1123456789", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1123456789", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("ABCDE@@123", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("ABCDE@@123", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1234AABCDE", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1234AABCDE", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1234aaBCDE", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new NoRepeatedCharRule(new ValidPassword()).isValidWithDetails("1234aaBCDE", new ArrayList<>()).getDetails().stream().findFirst().get());

    }
}
