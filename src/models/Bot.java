package models;

import botplayingstrategy.BotPlayingStrategy;
import botplayingstrategy.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(char symbol, String name, int id, PlayerType playertype, BotDifficultyLevel botDifficultyLevel) {
        super(symbol, name, id, playertype);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy =
                BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }
    @Override
    public Cell makeMove(Board board) {
        System.out.println("Get Ready for Sede-Sisya's Move ");
        Cell cell = botPlayingStrategy.makeMOve(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }



}
