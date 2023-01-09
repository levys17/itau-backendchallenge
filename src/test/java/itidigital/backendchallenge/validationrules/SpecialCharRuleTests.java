package itidigital.backendchallenge.validationrules;

import itidigital.backendchallenge.service.validationrules.SpecialCharRule;
import itidigital.backendchallenge.service.validationrules.ValidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpecialCharRuleTests {

    private static final String DETAIL_MESSAGE = "Password must contains at least one special character ( !@#$%^&*()-+ )";

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneSpecialChar(){
        assertTrue(new SpecialCharRule(new ValidPassword()).isValid("0123@4586789A"));
        assertTrue(new SpecialCharRule(new ValidPassword()).isValid("AB!D-+i@123"));
        assertTrue(new SpecialCharRule(new ValidPassword()).isValid("1234()BCDEF*HI"));
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutSpecialChar(){
        assertFalse(new SpecialCharRule(new ValidPassword()).isValid("123456789"));
        assertFalse(new SpecialCharRule(new ValidPassword()).isValid("abcde0123"));
        assertFalse(new SpecialCharRule(new ValidPassword()).isValid("1234abcde"));
    }

    @Test
    public void shouldReturnTrueForPasswordWithAtLeastOneSpecialCharAndEmptyDetails(){
        assertTrue(new SpecialCharRule(new ValidPassword()).isValidWithDetails("0123@4586789A", new ArrayList<>()).getIsValid());
        assertEquals(0,new SpecialCharRule(new ValidPassword()).isValidWithDetails("0123@4586789A", new ArrayList<>()).getDetails().size());

        assertTrue(new SpecialCharRule(new ValidPassword()).isValidWithDetails("AB!D-+i@123", new ArrayList<>()).getIsValid());
        assertEquals(0,new SpecialCharRule(new ValidPassword()).isValidWithDetails("AB!D-+i@123", new ArrayList<>()).getDetails().size());

        assertTrue(new SpecialCharRule(new ValidPassword()).isValidWithDetails("1234()BCDEF*HI", new ArrayList<>()).getIsValid());
        assertEquals(0,new SpecialCharRule(new ValidPassword()).isValidWithDetails("1234()BCDEF*HI", new ArrayList<>()).getDetails().size());
    }

    @Test
    public void shouldReturnFalseForPasswordWithoutSpecialCharAndDetailMessage(){
        assertFalse(new SpecialCharRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new SpecialCharRule(new ValidPassword()).isValidWithDetails("123456789", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new SpecialCharRule(new ValidPassword()).isValidWithDetails("abcde0123", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new SpecialCharRule(new ValidPassword()).isValidWithDetails("abcde0123", new ArrayList<>()).getDetails().stream().findFirst().get());

        assertFalse(new SpecialCharRule(new ValidPassword()).isValidWithDetails("1234abcde", new ArrayList<>()).getIsValid());
        assertEquals(DETAIL_MESSAGE, new SpecialCharRule(new ValidPassword()).isValidWithDetails("1234abcde", new ArrayList<>()).getDetails().stream().findFirst().get());

    }




}
