package AppRunner;

import Controller.GameController;
import exception.DuplicateSymbolException;
import exception.MoreThanOneBotException;
import exception.PlayerCountMissmatchException;
import models.*;
import stratagies.ColWinningStrategy;
import stratagies.DiagonalWinningStrategy;
import stratagies.RowWinningStrategy;
import stratagies.WiningStrategy;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws PlayerCountMissmatchException, DuplicateSymbolException, MoreThanOneBotException {
        GameController gameController = new GameController();

        int dimensions = 3;
        List<Player> playerList = new ArrayList<>();
        List<WiningStrategy> winningStrategies = new ArrayList<>();

        playerList.add(new Player('X',"Shathabdhi",1, PlayerType.HUMAN));
        playerList.add(new Bot('0',"GPT",2, PlayerType.BOT, BotDifficultyLevel.EASY));

        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(dimensions,playerList,winningStrategies);
        while (game.getGameState().equals(GameState.IN_PROG)){
            game.printBoard();
            gameController.makeMove(game);
        }
        //if im here game state is not in progress
        if (GameState.SUCCESS.equals(game.getGameState())){
            System.out.println(game.getWinner().getName()+", Congrats! You are the Winner :) -- ");

        }
        if (GameState.DRAWN.equals(game.getGameState())){
            System.out.println("Match tied :| ");
        }
    }
}
