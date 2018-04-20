package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    private Answer answer;

    @Before
    public void setUp() throws Exception {
        answer = new Answer();
    }

    @Test
    public void should_get_an_answer_when_input_a_string() {
        // Given
        String inputStr = "1 2 3 4";
        Answer newAnswer = null;

        // When
        newAnswer = Answer.createAnswer(inputStr);

        // Then
        assertNotNull(newAnswer);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void should_throw_exception_when_numList_is_not_validate() throws OutOfRangeAnswerException {
        // Given
        String inputStr = "1 2 3 3";
        List<String> inputList = Arrays.stream(inputStr.split(" ")).collect(Collectors.toList());
        answer.setNumList(inputList);

        // Then
        expectedEx.expect(OutOfRangeAnswerException.class);
        expectedEx.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void should_throws_no_exception_when_numList_is_validate() throws OutOfRangeAnswerException {
        // Given
        String inputStr = "1 2 3 4";
        List<String> inputList = Arrays.stream(inputStr.split(" ")).collect(Collectors.toList());
        answer.setNumList(inputList);

        // Then
        answer.validate();
    }

    @Test
    public void should_get_a_record_when_input_an_answer() {
        // Given
        String inputStr = "1 2 3 4";
        List<String> inputList = Arrays.stream(inputStr.split(" ")).collect(Collectors.toList());
        answer.setNumList(inputList);
        Answer inputAnswer = Answer.createAnswer("1 2 3 3");

        // When
        int[] value = answer.check(inputAnswer).getValue();
        int[] expectedValue = new int[]{3, 0};

        // Then
        assertArrayEquals(expectedValue, value);
    }

    @Test
    public void should_get_an_index_when_input_a_num() {
        // Given
        String inputStr = "1 2 3 4";
        List<String> inputList = Arrays.stream(inputStr.split(" ")).collect(Collectors.toList());
        answer.setNumList(inputList);

        // Then
        assertEquals(2, answer.getIndexOfNum("3"));
    }

    @Test
    public void should_get_a_string_when_answer_transform_the_numList() {
        // Given
        String inputStr = "1 2 3 4";
        List<String> inputList = Arrays.stream(inputStr.split(" ")).collect(Collectors.toList());
        answer.setNumList(inputList);

        // Then
        assertEquals(inputStr, answer.toString());
    }
}