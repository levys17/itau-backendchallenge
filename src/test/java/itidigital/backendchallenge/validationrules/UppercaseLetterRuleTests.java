package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.UppercaseLetterRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UppercaseLetterRuleTests {

    private static final String DETAIL_MESSAGE = "Password must contains at least one uppercase character";

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneUppercaseChar(){
        assertTrue(new UppercaseLetterRule(new ValidPassword()).isValid("01234586789A"));
        assertTrue(new UppercaseLetterRule(new ValidPassword()).isValid("ABCDghi@123"));
        assertTrue(new UppercaseLetterRule(new ValidPassword()).isValid("1234@BCDEFGHI"));
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutUppercaseChar(){
        assertFalse(new UppercaseLetterRule(new ValidPassword()).isValid("123456789"));
        assertFalse(new UppercaseLetterRule(new ValidPassword()).isValid("abcde@123"));
        assertFalse(new UppercaseLetterRule(new ValidPassword()).isValid("1234abcde"));
    }

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneUppercaseCharAndEmptyDetails(){
        assertTrue(new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("01234586789A", new ArrayList<>()).getIsValid());
        assertTrue(new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("ABCDghi@123", new ArrayList<>()).getIsValid());
        assertTrue(new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("1234@BCDEFGHI", new ArrayList<>()).getIsValid());

        assertEquals(0, new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("01234586789A", new ArrayList<>()).getDetails().size());
        assertEquals(0, new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("ABCDghi@123", new ArrayList<>()).getDetails().size());
        assertEquals(0, new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("1234@BCDEFGHI", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutUppercaseCharAndDetailMessage(){
        assertFalse(new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getIsValid());
        assertFalse(new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("abcde@123", new ArrayList<>()).getIsValid());
        assertFalse(new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("1234abcde", new ArrayList<>()).getIsValid());

        assertEquals(DETAIL_MESSAGE, new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getDetails().stream().findFirst().get());
        assertEquals(DETAIL_MESSAGE, new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("abcde@123", new ArrayList<>()).getDetails().stream().findFirst().get());
        assertEquals(DETAIL_MESSAGE, new UppercaseLetterRule(new ValidPassword()).isValidWithDetails("1234abcde", new ArrayList<>()).getDetails().stream().findFirst().get());
    }
}
