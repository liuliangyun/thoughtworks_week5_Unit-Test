package tw.core.generator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Before
    public void setUp() throws Exception {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void should_generate_an_answer() throws OutOfRangeAnswerException {
        // Given
        int digitalmax = 9;
        int numbersOfNeed = 4;
        String inputStr = "1 2 3 4";
        Answer answer = null;

        // When
        when(randomIntGenerator.generateNums(digitalmax, numbersOfNeed)).thenReturn(inputStr);
        answer = answerGenerator.generate();

        // Then
        assertNotNull(answer);
    }
}

