package itidigital.backendchallenge;

import itidigital.backendchallenge.service.PasswordValidationService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordValidationServiceTests {

    @Autowired
    private PasswordValidationService passwordValidationService;

    private static final String LOWERCASE_DETAIL = "Password must contains at least one lowercase character";
    private static final String NO_REPEATED_DETAIL = "Password must not contains repeated characters";
    private static final String NO_WHITESPACE_DETAIL = "Password must not contains whitespace";
    private static final String ONE_DIGIT_DETAIL = "Password must contains at least one digit";
    private static final String LENGTH_DETAIL = "Password must contains at least 9 characters";
    private static final String SPECIAL_CHAR_DETAIL = "Password must contains at least one special character ( !@#$%^&*()-+ )";
    private static final String UPPERCASE_DETAIL = "Password must contains at least one uppercase character";

    @Test
    public void shouldReturnTrueWhenPasswordIsValid(){
        assertTrue(passwordValidationService.validatePassword("AbTp9!fok").getIsValid());
        assertTrue(passwordValidationService.validatePassword("12345@Abcd").getIsValid());
        assertTrue(passwordValidationService.validatePassword("abcD+12345").getIsValid());
        assertTrue(passwordValidationService.validatePassword("XyZ*-@!01234").getIsValid());
    }

    @Test
    public void shouldReturnFalseWhenPasswordIsNotValid(){
        assertFalse(passwordValidationService.validatePassword("").getIsValid());
        assertFalse(passwordValidationService.validatePassword("aa").getIsValid());
        assertFalse(passwordValidationService.validatePassword("ab").getIsValid());
        assertFalse(passwordValidationService.validatePassword("AAAbbbCc").getIsValid());
        assertFalse(passwordValidationService.validatePassword("AbTp9!foo").getIsValid());
        assertFalse(passwordValidationService.validatePassword("AbTp9!foA").getIsValid());
        assertFalse(passwordValidationService.validatePassword("AbTp9 fok").getIsValid());

    }

    @Test
    public void shouldReturnFalseAndValidationErrorsDetailWhenPasswordIsNotValid(){
        assertEquals(LOWERCASE_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(LOWERCASE_DETAIL::equals).findAny().get());
        assertEquals(NO_REPEATED_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(NO_REPEATED_DETAIL::equals).findAny().get());
        assertEquals(NO_WHITESPACE_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(NO_WHITESPACE_DETAIL::equals).findAny().get());
        assertEquals(ONE_DIGIT_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(ONE_DIGIT_DETAIL::equals).findAny().get());
        assertEquals(LENGTH_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(LENGTH_DETAIL::equals).findAny().get());
        assertEquals(SPECIAL_CHAR_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(SPECIAL_CHAR_DETAIL::equals).findAny().get());
        assertEquals(UPPERCASE_DETAIL,passwordValidationService.validatePasswordWithDetails("  ").getDetails().stream().filter(UPPERCASE_DETAIL::equals).findAny().get());
    }
}
