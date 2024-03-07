package models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private String name ;
    private int id;
    private Scanner scanner;

    public Player(char symbol, String name, int id, PlayerType playertype) {
        this.symbol = symbol;
        this.name = name;
        this.id = id;
        this.playertype = playertype;
        scanner = new Scanner(System.in);
    }




    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayertype() {
        return playertype;
    }

    public void setPlayertype(PlayerType playertype) {
        this.playertype = playertype;
    }
    private PlayerType playertype;

    public Cell makeMove(Board board) {
        System.out.println(this.name+", It's your turn to make the move (◣_◢), enter row and col");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while (validateMove(row,col,board)==false){
            System.out.println(this.name + ", Invalid move, please try again .");

            row = scanner.nextInt();
            col = scanner.nextInt();
        }
        //now we have a valid move
        Cell  cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;

    }

    private boolean validateMove(int row, int col, Board board) {
        if (row>= board.getDimension() || row<0){
            return false;
        }
        if (col >= board.getDimension() || col<0){
            return false;
        }
        //it compares cell states(CellState.EMPTY.equals) with actual cell state using rows and col
        if(!CellState.EMPTY.equals(board.getBoard().get(row).get(col).getCellState())){
            return false;
        }
        return true;
    }

}
