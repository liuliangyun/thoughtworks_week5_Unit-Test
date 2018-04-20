package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.generator.AnswerGenerator;
import tw.core.model.Record;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    private static final int MAX_TIMES = 6;
    private Answer actualAnswer;
    private AnswerGenerator answerGenerator;
    private Game game;

    @Before
    public void setUp() throws Exception {
        answerGenerator = mock(AnswerGenerator.class);
        actualAnswer = mock(Answer.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);
    }

    @Test
    public void should_get_a_result_when_guess_the_input_answer() {
        // Given
        Answer inputAnswer = mock(Answer.class);
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseIncludeOnlyNum();
        record.increaseIncludeOnlyNum();

        int[] array = new int[]{1, 2};
        String expected = String.format("%1$sA%2$sB", array[0], array[1]);

        // When
        when(actualAnswer.check(inputAnswer)).thenReturn(record);

        // Then
        assertEquals(expected, game.guess(inputAnswer).getResult());
    }

    @Test
    public void length_add_1_when_guess_the_input_answer() {
        // Given
        Answer inputAnswer = mock(Answer.class);
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseIncludeOnlyNum();
        record.increaseIncludeOnlyNum();

        // When
        when(actualAnswer.check(inputAnswer)).thenReturn(record);
        int expected = game.guessHistory().size();
        game.guess(inputAnswer);

        // Then
        assertEquals(expected+1, game.guessHistory().size());
    }

    @Test
    public void should_get_fail_when_reach_6() {
        // Given
        Answer inputAnswer = mock(Answer.class);
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseIncludeOnlyNum();
        record.increaseIncludeOnlyNum();

        // When
        when(actualAnswer.check(inputAnswer)).thenReturn(record);
        for (int i = 0; i < MAX_TIMES; i++) {
            game.guess(inputAnswer);
        }

        // Then
        assertEquals("fail", game.checkStatus());
    }

    @Test
    public void should_get_success_when_input_correct_result() {
        // Given
        Answer inputAnswer = mock(Answer.class);
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseCurrentNum();
        record.increaseCurrentNum();
        record.increaseCurrentNum();

        // When
        when(actualAnswer.check(inputAnswer)).thenReturn(record);
        for (int i = 0; i < MAX_TIMES; i++) {
            game.guess(inputAnswer);
            if (game.checkStatus().equals("success")){
                break;
            }
        }

        // Then
        assertEquals("success", game.checkStatus());
    }

    @Test
    public void should_get_continue_when_input_error_result_but_have_more_chances() {
        // Given
        Answer inputAnswer = mock(Answer.class);
        Record record = new Record();
        record.increaseCurrentNum();
        record.increaseCurrentNum();


        // When
        when(actualAnswer.check(inputAnswer)).thenReturn(record);
        game.guess(inputAnswer);

        // Then
        assertEquals("continue", game.checkStatus());
    }
}
