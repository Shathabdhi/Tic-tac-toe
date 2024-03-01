package botplayingstrategy;

import models.Board;
import models.Cell;

public interface BotPlayingStrategy {
    //takes te board and returns the cell
    Cell makeMOve(Board board);
}
