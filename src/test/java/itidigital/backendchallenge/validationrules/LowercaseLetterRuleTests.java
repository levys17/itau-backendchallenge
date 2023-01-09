package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.LowercaseLetterRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LowercaseLetterRuleTests {

    private static final String DETAIL_MESSAGE = "Password must contains at least one lowercase character";

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneLowercaseChar(){
        assertTrue(new LowercaseLetterRule(new ValidPassword()).isValid("012345a6789"));
        assertTrue(new LowercaseLetterRule(new ValidPassword()).isValid("ABCDghi@123"));
        assertTrue(new LowercaseLetterRule(new ValidPassword()).isValid("1234@bcdefghi"));
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutLowercaseChar(){
        assertFalse(new LowercaseLetterRule(new ValidPassword()).isValid("123456789"));
        assertFalse(new LowercaseLetterRule(new ValidPassword()).isValid("ABCDE@123"));
        assertFalse(new LowercaseLetterRule(new ValidPassword()).isValid("1234ABCDE"));
    }

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneLowercaseCharAndEmptyDetails(){
        assertTrue(new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("012345a6789", new ArrayList<>()).getIsValid());
        assertEquals(0,new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("012345a6789", new ArrayList<>()).getDetails().size());

        assertTrue(new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("ABCDghi@123", new ArrayList<>()).getIsValid());
        assertEquals(0,new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("ABCDghi@123", new ArrayList<>()).getDetails().size());

        assertTrue(new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("1234@bcdefghi", new ArrayList<>()).getIsValid());
        assertEquals(0,new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("1234@bcdefghi", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutLowercaseCharAndDetailMessage(){
        assertFalse(new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("ABCDE@123", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("ABCDE@123", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("1234ABCDE", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new LowercaseLetterRule(new ValidPassword()).isValidWithDetails("1234ABCDE", new ArrayList<>()).getDetails().stream().findFirst().get());

    }
}
