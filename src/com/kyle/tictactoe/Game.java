package com.kyle.tictactoe;

import java.util.Random;

public class Game {

    private Board board;
    private Random random;

    public Game(){
        initializeGame();
        displayBoard();
        makeFirstMove();
        playGame();
        checkStatus();
    }

    private void initializeGame() {
        this.board = new Board();
        this.random = new Random();
        this.board.setupBoard();
    }

    private void displayBoard() {
        this.board.displayBoard();
    }

    private void makeFirstMove() {

        System.out.print("Who starts? 1 - COMPUTER ; 2 - USER");
        int choice = board.getScanner().nextInt();

        if(choice == 1){
            Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
            board.move(cell, CellState.COMPUTER);
        }
    }

    private void playGame() {

        while(board.isRunning()){

            board.displayBoard();
            System.out.println("User move:");
            Cell userCell = new Cell(board.getScanner().nextInt(), board.getScanner().nextInt());

            board.move(userCell, CellState.USER);
            board.displayBoard();

            if(!board.isRunning()){
                break;
            }

            board.callMinimax(0, CellState.COMPUTER);

            for(Cell cell : board.getRootValues()){
                System.out.println("Cell values: " + cell +" minimaxValue: " + cell.getMinimaxValue());

            }

            board.move(board.getBestMove(), CellState.COMPUTER);
            board.displayBoard();
        }
    }

    private void checkStatus() {
        if(board.isWinning(CellState.COMPUTER))
            System.out.print("Computer has won...");
        else if(board.isWinning(CellState.USER))
            System.out.print("User has won...");
        else
            System.out.print("It is a draw...");
    }

}
