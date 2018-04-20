package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private Game game;
    private GameView gameView;
    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        game = mock(Game.class);
        gameView = mock(GameView.class);
        gameController = new GameController(game, gameView);
    }

    @Test
    public void should_print_message_when_begin_game() throws IOException {
        gameController.beginGame();
        verify(gameView,times(1)).showBegin();
    }

    @Test
    public void should_when_check_true() throws IOException {
        // Given
        InputCommand command = mock(InputCommand.class);

        // When
        when(command.input()).thenReturn(new Answer());
        when(game.checkCoutinue()).thenReturn(true,false);
        gameController.play(command);

        // Then
        verify(game,times(2)).checkCoutinue();
        verify(game,times(1)).guess(command.input());
        verify(game,times(1)).guessHistory();
        verify(game,times(1)).checkStatus();
        verify(gameView,times(1)).showGuessResult(game.guess(command.input()));
        verify(gameView,times(1)).showGuessHistory(game.guessHistory());
        verify(gameView,times(1)).showGameStatus(game.checkStatus());
    }
}