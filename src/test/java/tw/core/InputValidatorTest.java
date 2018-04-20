package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        inputValidator = new InputValidator();
    }

    @Test
    public void should_get_false_when_numstr_integer_is_repeat() {
        // Given
        String numstr = "1 1 2 3";

        // Then
        assertFalse(inputValidator.validate(numstr));
    }

    @Test
    public void should_get_false_when_numstr_integer_length_is_not_4() {
        // Given
        String numstr = "1 2 3";

        // Then
        assertFalse(inputValidator.validate(numstr));
    }

    @Test
    public void should_get_true_when_numstr_is_validate() {
        // Given
        String numstr = "1 2 3 4";

        // Then
        assertTrue(inputValidator.validate(numstr));
    }
}
