package stratagies;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WiningStrategy{

    Map<Character,Integer> leftDiagonalMap = new HashMap<>();
    Map<Character,Integer> rightDiagonalMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        char symbol = move.getPlayer().getSymbol();

        //check if cell is part of left diagonal
        if (row == col){
            //check if this symbol is comming for the first time in left diagonal
            if (!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol,0);
            }
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)+1);
            // check if the count of current symbol is same as size of board
            if (board.getDimension()==leftDiagonalMap.get(symbol)){
                System.out.println("Winning via left diag");
                return true;
            }
        }

        //check if the cell id the part of left diagonal
        if ((row+col)==(board.getDimension()-1)){
            if (!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);
            }

            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)+1);
            // check if the count of current symbol is same as size of board
            if(board.getDimension()==rightDiagonalMap.get(symbol)){
                System.out.println("Winning via right diag");
                return true;
            }
        }
        return false;
    }
}
