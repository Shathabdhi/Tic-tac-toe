package models;

import exception.DuplicateSymbolException;
import exception.MoreThanOneBotException;
import exception.PlayerCountMissmatchException;
import stratagies.WiningStrategy;

import javax.swing.*;
import java.util.*;

public class Game {
    private List<Player> playerlist;

    private Board board;
    private List<Move> moves;
    private Player winner;

    private GameState gameState;

    private int nexPlayerIndex;

    private List<WiningStrategy> winingStrategies;

    private Game(int dimension, List<Player> playerlist, List<WiningStrategy> winingStrategies) {
        this.board = new Board(dimension);
        // we are going to build a new board
        this.playerlist = playerlist;
        this.winingStrategies = winingStrategies;
        this.gameState = GameState.IN_PROG;
        this.nexPlayerIndex = 0;
        this.moves = new ArrayList<>();

    }

    public static Builder getBuilder(){
        return new Builder();
    }
    public void printBoard() {
        board.printBoard();
    }

    public void makeMove(){
        Player player = playerlist.get(nexPlayerIndex);
        Cell cell = player.makeMove(board);
        Move move = new Move(cell,player);
        moves.add(move);
        if (checkWinner(move,board)){
            gameState = GameState.SUCCESS;
            winner = player;
            return;
        }
        /*----------------------Check for draw ----------------------------------------------*/
        if (moves.size()== board.getDimension()*board.getDimension()){
            gameState = GameState.DRAWN;
            return;
        }
        //update the next player accordingly
        nexPlayerIndex ++;
        nexPlayerIndex = nexPlayerIndex % playerlist.size();

    }

    private boolean checkWinner(Move move, Board board) {
        for (WiningStrategy winningStrategy : winingStrategies){
            if (winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }


    //Builder pattern to create a game
    public static class Builder{
       private List<Player> players;
       private List<WiningStrategy> winingStrategies;
       private int dimemsion;

        public Builder() {
            //builder will have private constructor
            this.players = new ArrayList<>();
            this.winingStrategies = new ArrayList<>();
            this.dimemsion = 0;
        }
        public Game build() throws MoreThanOneBotException, DuplicateSymbolException, PlayerCountMissmatchException {
            //the class builder is trying to build is Game
            /*
                1. Validate the bot count <=1
                2. Validate unique symbol for player
                3. Validate dimension and playercount (n+1 = dimension)
            */
            validateBotCount();
            ValidateUniqueSymbolForPlayer();
            ValidateDimensionandPlayerCount();
            return new Game( dimemsion,players,winingStrategies);
        }
        private void validateBotCount() throws MoreThanOneBotException{
            int botCount = 0;
            for (Player player:players){
                if (player.getPlayertype().equals(PlayerType.BOT)){
                    botCount++ ;
                }
            }
            if (botCount>1){
                throw new MoreThanOneBotException();
            }
        }
        private void ValidateUniqueSymbolForPlayer() throws DuplicateSymbolException {
            Set<Character> symbols = new HashSet<>();
            //check if exists or not before throwing exception

            for (Player player: players){
                if (symbols.contains(player.getSymbol())){
                    throw new DuplicateSymbolException();
                }
                else {
                    symbols.add(player.getSymbol());
                }
            }
        }
        private void ValidateDimensionandPlayerCount() throws PlayerCountMissmatchException {
            //the board dimensions is always player+1
            if (players.size() != (dimemsion-1)){
                throw new PlayerCountMissmatchException();
            }

        }

        public Builder setPlayers(List<Player> players) {
            //pass the player
            this.players = players;
            return this;
        }

        public Builder setWiningStrategies(List<WiningStrategy> winingStrategies) {
            //pass the Winning Strategy
            this.winingStrategies = winingStrategies;
            return this;
        }

        public Builder setDimemsion(int dimemsion) {
            //pass the dimension
            this.dimemsion = dimemsion;
            return this;
        }
    }
    public List<Player> getPlayerlist(){
        return playerlist;
    }

    public void setPlayerlist(List<Player> playerlist) {
        this.playerlist = playerlist;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMove() {
        return moves;
    }

    public void setMove(List<Move> move) {
        this.moves = move;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNexPlayerINdex() {
        return nexPlayerIndex;
    }

    public void setNexPlayerINdex(int nexPlayerINdex) {
        this.nexPlayerIndex = nexPlayerINdex;
    }
}
