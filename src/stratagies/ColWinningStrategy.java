package stratagies;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WiningStrategy{

    //@Override
    Map<Integer, Map<Character,Integer>> map = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();

        //check if current col is present in the hashmap
        if (!map.containsKey(col)){
            map.put(col,new HashMap<>());
        }
        Map<Character,Integer> colMap = map.get(col);

        //first time entry for the symbol
        if (!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }

        //update the symbol count
        colMap.put(symbol,colMap.get(symbol)+1);

        //check if the symbol of count has reached the size of the board
        if(board.getBoard().size()==(colMap.get(symbol))){
            return true;
        }
        return false;
    }
}
