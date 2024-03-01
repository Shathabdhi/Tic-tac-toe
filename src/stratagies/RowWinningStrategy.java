package stratagies;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WiningStrategy{

    Map<Integer, Map<Character,Integer>> map = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();

        //check if current row is present in the hashmap
        if (!map.containsKey(row)){
            map.put(row,new HashMap<>());
        }
        Map<Character,Integer> rowMap = map.get(row);

        //first time entry for the symbol
        if (!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }

        //update the symbol count
        rowMap.put(symbol,rowMap.get(symbol)+1);

        //check if the symbol of count has reached the size of the board
        if (board.getBoard().size()==(rowMap.get(symbol))){
            return true;
        }
        return false;
    }
}
