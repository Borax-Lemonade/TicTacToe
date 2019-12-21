import java.util.Dictionary;
import java.util.Map;

public class Dooley_Jon extends Player {

    public static char ourPlayer;
    public static char enemyPlayer;

    public static int ourScore = 10;
    public static int enemyScore = -10;

    public void calcMove() {
        int nextMoveRow = 0;
        int nextMoveColumn = 0;
        
        ourPlayer = getTeam();
        if (ourPlayer == 'X') { enemyPlayer = 'O'; }
        if (ourPlayer == 'O') { enemyPlayer = 'X'; }
    	
        char[][] gameBoard = getGameState();
        
        // get the best possible move using the minimax algorithm:
        char[][] gameBoardCopy = gameBoard;
        double bestVal = Double.NEGATIVE_INFINITY;

        // loop through all cells in gameboard
        for (int row = 0; row < 3; row++) {
            for (int column = 0 ; column < 3; column++) {

                // check if cell is empty
                if (!(gameBoardCopy[row][column] == 'X' || gameBoardCopy[row][column] == 'O')) {

                    // save the current board value
                    char boardValue = gameBoardCopy[row][column];

                    // make move at this empty cell
                    gameBoardCopy[row][column] = ourPlayer;

                    // compute evaluation for this move
                    int value = miniMax(gameBoardCopy, 0, false);

                    // undo the move
                    gameBoardCopy[row][column] = boardValue;

                    // if the value of the current move is more
                    // than the best value, then update the best
                    if (value > bestVal) {
                        nextMoveRow = row;
                        nextMoveColumn = column;
                        bestVal = value;
                    }
                }
            }
        }
        
        
        submitMove(nextMoveRow, nextMoveColumn);

    }

    // Return null if game is not in a terminal state,
    // return public static variable "ourScore" if game is won by our player
    // return public static variable "enemyScore" if game is won by enemy player
    public int terminalState(char[][] gameBoardCopy) {

        // loop through every top column on the board
        for (int column = 0; column < 3; column++) {
            // if the current column all the way down is filled with our player's char, we won
            if (gameBoardCopy[0][column] == ourPlayer) {
                if (gameBoardCopy[0][column] == ourPlayer && 
                        gameBoardCopy[1][column] == ourPlayer &&
                        gameBoardCopy[2][column] == ourPlayer) {
                            return ourScore;
                } 
            }

            // if the current column all the way down is filled with our enemy's char, they won
            else if (gameBoardCopy[0][column] == enemyPlayer) {
                if (gameBoardCopy[0][column] == enemyPlayer && 
                        gameBoardCopy[1][column] == enemyPlayer &&
                        gameBoardCopy[2][column] == enemyPlayer) {
                            return enemyScore;
                } 
            }
        }

        // loop through every row on the board
        for (int row = 0; row < 3; row++) {
            // if the current row all the way to the right is filled with our player's char, we won
            if (gameBoardCopy[row][0] == ourPlayer) {
                if (gameBoardCopy[row][0] == ourPlayer &&
                        gameBoardCopy[row][1] == ourPlayer &&
                        gameBoardCopy[row][2] == ourPlayer) {
                            return ourScore;
                }
            }

            // if the current row all the way to the right is filled with our enemy's char, they won
            if (gameBoardCopy[row][0] == ourPlayer) {
                if (gameBoardCopy[row][0] == ourPlayer &&
                        gameBoardCopy[row][1] == ourPlayer &&
                        gameBoardCopy[row][2] == ourPlayer) {
                            return ourScore;
                }
            }
        }

        // check diagonals
        if (gameBoardCopy[0][0] == ourPlayer &&
                gameBoardCopy[1][1] == ourPlayer &&
                gameBoardCopy[2][2] == ourPlayer) {
                    return ourScore;
        } else if (gameBoardCopy[0][2] == ourPlayer &&
                    gameBoardCopy[1][1] == ourPlayer &&
                    gameBoardCopy[2][0] == ourPlayer) {
                        return ourScore;
        } else if (gameBoardCopy[0][0] == enemyPlayer &&
                    gameBoardCopy[1][1] == enemyPlayer &&
                    gameBoardCopy[2][2] == enemyPlayer) {
                        return enemyScore;
        } else if (gameBoardCopy[0][2] == enemyPlayer &&
                    gameBoardCopy[1][1] == enemyPlayer &&
                    gameBoardCopy[2][0] == enemyPlayer) {
                        return enemyScore;
        }

        return 0;
    }

    public boolean isMovesLeft(char[][] gameBoardCopy) {
        int count = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (gameBoardCopy[row][column] == 'X' ||
                    gameBoardCopy[row][column] == 'O') {
                        count++;
                    }
            }
        }
        
        if (count == 9) {
            return false;
        } else {
            return true;
        }
    }

    public int miniMax(char[][] gameBoardCopy, int depth, boolean isMaximizingPlayer) {

        // check to see if the game is in a terminal state
        int state = terminalState(gameBoardCopy);
        if (state == ourScore) {
            return ourScore;
        } else if (state == enemyScore) {
            return enemyScore;
        } else if (isMovesLeft(gameBoardCopy) == false) {
            return 0;
        }

        // if this is the maximizer's move
        if (isMaximizingPlayer) {
            double bestVal = -1000;

            // loop through all cells in board
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {

                    // check if cell is empty
                    if (!(gameBoardCopy[row][column] == 'X' || gameBoardCopy[row][column] == 'O')) {
                        // save the current board value
                        char boardValue = gameBoardCopy[row][column];

                        // try to make a move there
                        gameBoardCopy[row][column] = ourPlayer;

                        // get the score of the move we just made by recursively calling the
                        // miniMax algorithm
                        bestVal = Math.max(bestVal, miniMax(gameBoardCopy, depth+1, !isMaximizingPlayer));

                        // undo the move
                        gameBoardCopy[row][column] = boardValue;
                    }

                }
            }
            return (int)bestVal;
        }

        // if this is the minimizer's move
        else {
            double bestVal = 1000;

            // loop through all cells in board
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {

                    // check if cell is empty
                    if (!(gameBoardCopy[row][column] == 'X' || gameBoardCopy[row][column] == 'O')) {
                        // save the current board value
                        char boardValue = gameBoardCopy[row][column];

                        // try to make a move there
                        gameBoardCopy[row][column] = enemyPlayer;

                        // get the score of the move we just made by recursively calling the miniMax algorithm
                        bestVal = Math.min(bestVal, minimax(gameBoardCopy, depth+1, !isMaximizingPlayer));

                        // undo the move
                        gameBoardCopy[row][column] = boardValue;
                    }
                }
            }
            return (int)bestVal;
        }
    }

}