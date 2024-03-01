package Controller;

import exception.DuplicateSymbolException;
import exception.MoreThanOneBotException;
import exception.PlayerCountMissmatchException;
import models.Game;
import models.Player;
import stratagies.WiningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> playerList,List<WiningStrategy> winningstrategy) throws PlayerCountMissmatchException, DuplicateSymbolException, MoreThanOneBotException {

        return Game.getBuilder()
                .setDimemsion(dimension)
                .setPlayers(playerList)
                .setWiningStrategies(winningstrategy)
                .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){

        game.makeMove();
    }
}
