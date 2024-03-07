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
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws PlayerCountMissmatchException, DuplicateSymbolException, MoreThanOneBotException {
        GameController gameController = new GameController();
        Scanner scn = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the dimension");
        int dimensions = scn.nextInt();
        List<Player> playerList = new ArrayList<>();
        List<WiningStrategy> winningStrategies = new ArrayList<>();

        System.out.println("Please enter your name");
        String name = scn.next();
        playerList.add(new Player('X',name,1, PlayerType.HUMAN));
        playerList.add(new Bot('0',"BOT",2, PlayerType.BOT, BotDifficultyLevel.EASY));

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
            System.out.println(game.getWinner().getName()+", Congrats! You are the Winner ε(´｡•᎑•`)っ \uD83D\uDC95 ");
        }
        if (GameState.DRAWN.equals(game.getGameState())){
            System.out.println("Match tied :| ");
        }
    }
}
