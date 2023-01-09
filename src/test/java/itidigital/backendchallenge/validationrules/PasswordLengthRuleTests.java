package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.PasswordLengthRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordLengthRuleTests {

    private static final String DETAIL_MESSAGE = "Password must contains at least 9 characters";

    @Test
    public void shouldReturnTrueForPasswordLengthGreaterThan9(){
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValid("0123456789"));
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValid("aAdEfghi@123"));
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValid("1234aBcdeFghi"));
    }

    @Test
    public void shouldReturnTrueForPasswordLengthEqualsTo9(){
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValid("123456789"));
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValid("ABCde@123"));
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValid("1234AbcDe"));
    }

    @Test
    public void shouldReturnFalseForPasswordLengthLessThan9(){
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValid("012345"));
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValid("A@12"));
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValid("12 aB"));
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValid("          a"));
    }

    @Test
    public void shouldReturnFalseForEmptyStringPassword(){
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValid(""));
    }

    @Test
    public void shouldReturnTrueForPasswordLengthGreaterThan9AndEmptyDetails(){
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("0123456789", new ArrayList<>()).getIsValid());
        assertEquals(0,new PasswordLengthRule(new ValidPassword()).isValidWithDetails("0123456789", new ArrayList<>()).getDetails().size());

        assertTrue(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("aAdEfghi@123", new ArrayList<>()).getIsValid());
        assertEquals(0,new PasswordLengthRule(new ValidPassword()).isValidWithDetails("aAdEfghi@123", new ArrayList<>()).getDetails().size());

        assertTrue(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("1234aBcdeFghi", new ArrayList<>()).getIsValid());
        assertEquals(0,new PasswordLengthRule(new ValidPassword()).isValidWithDetails("1234aBcdeFghi", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnTrueForPasswordLengthEqualsTo9AndEmptyDetails(){
        assertTrue(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getIsValid());
        assertEquals(0,new PasswordLengthRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getDetails().size());

        assertTrue(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("ABCde@123", new ArrayList<>()).getIsValid());
        assertEquals(0,new PasswordLengthRule(new ValidPassword()).isValidWithDetails("ABCde@123", new ArrayList<>()).getDetails().size());

        assertTrue(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("1234AbcDe", new ArrayList<>()).getIsValid());
        assertEquals(0,new PasswordLengthRule(new ValidPassword()).isValidWithDetails("1234AbcDe", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordLengthLessThan9AndDetailMessage(){
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("012345", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new PasswordLengthRule(new ValidPassword()).isValidWithDetails("012345", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("A@12", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new PasswordLengthRule(new ValidPassword()).isValidWithDetails("A@12", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("12 aB", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new PasswordLengthRule(new ValidPassword()).isValidWithDetails("12 aB", new ArrayList<>()).getDetails().stream().findFirst().get());

    }

    @Test
    public void shouldReturnFalseForEmptyStringPasswordAndDetailMessage(){
        assertFalse(new PasswordLengthRule(new ValidPassword()).isValidWithDetails("", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new PasswordLengthRule(new ValidPassword()).isValidWithDetails("", new ArrayList<>()).getDetails().stream().findFirst().get());
    }
}
