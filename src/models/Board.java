package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;
    public Board(int dimension) {
        this.dimension = dimension;
        board = new ArrayList<>();//initialise to new array list
        /*[empty array is created]*/

        for(int i=0;i<dimension;i++){
            board.add(new ArrayList<>());
            /*[[add new empty array]]*///if player = 2 then dimension = 2+1
            /*[[add new empty array]]*/
            /*[[add new empty array]]*/


            for (int j=0;j<dimension;j++){
                board.get(i).add(new Cell(i, j));
                /*add address of all dimensions*/
                /*[[0,0],[0,1],[0,2]]*/
                /*[[1,0],[1,1],[1,2]]*/
                /*[[2,0],[2,1],[2,2]]*/
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void printBoard() {
        for (List<Cell> row: board){
            for (Cell cell : row){
                cell.display();
            }
            System.out.println();
        }
    }
}
