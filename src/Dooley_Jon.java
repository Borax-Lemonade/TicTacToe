import java.util.Dictionary;
import java.util.Map;
import java.lang.Math;

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

        // if middle space is empty, go there
        if (!(gameBoardCopy[1][1] == enemyPlayer) ||
            !(gameBoardCopy[1][1] == ourPlayer)) {
            submitMove(1, 1);
        }
        // else if not, proceed with minimax
        else {
            // if middle block is empty, go there
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
                        double value = miniMax(gameBoardCopy, false);

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

    }

    // Return null if game is not in a terminal state,
    // return public static variable "ourScore" if game is won by our player
    // return public static variable "enemyScore" if game is won by enemy player
    public int terminalState(char[][] gameBoardCopy) {
        // check columns
        if (gameBoardCopy[0][0] == ourPlayer &&
                gameBoardCopy[1][0] == ourPlayer &&
                gameBoardCopy[2][0] == ourPlayer) {
                    return ourScore;
        } else if (gameBoardCopy[0][1] == ourPlayer &&
                gameBoardCopy[1][1] == ourPlayer &&
                gameBoardCopy[2][1] == ourPlayer) {
                    return ourScore;
        } else if (gameBoardCopy[0][2] == ourPlayer &&
                gameBoardCopy[1][2] == ourPlayer &&
                gameBoardCopy[2][2] == ourPlayer) {
                    return ourScore;
        } 
        
        else if (gameBoardCopy[0][0] == enemyPlayer &&
                gameBoardCopy[1][0] == enemyPlayer &&
                gameBoardCopy[2][0] == enemyPlayer) {
                    return enemyScore;
        } else if (gameBoardCopy[0][1] == enemyPlayer &&
                gameBoardCopy[1][1] == enemyPlayer &&
                gameBoardCopy[2][1] == enemyPlayer) {
                    return enemyScore;
        } else if (gameBoardCopy[0][2] == enemyPlayer &&
                gameBoardCopy[1][2] == enemyPlayer &&
                gameBoardCopy[2][2] == enemyPlayer) {
                    return enemyScore;
        }


        // check rows
        else if (gameBoardCopy[0][0] == ourPlayer &&
                gameBoardCopy[0][1] == ourPlayer &&
                gameBoardCopy[0][2] == ourPlayer) {
                    return ourScore;
        } else if (gameBoardCopy[1][0] == ourPlayer &&
                gameBoardCopy[1][1] == ourPlayer &&
                gameBoardCopy[1][2] == ourPlayer) {
                    return ourScore;
        } else if (gameBoardCopy[2][0] == ourPlayer &&
                gameBoardCopy[2][1] == ourPlayer &&
                gameBoardCopy[2][2] == ourPlayer) {
                    return ourScore;
        }

        else if (gameBoardCopy[0][0] == enemyPlayer &&
                gameBoardCopy[0][1] == enemyPlayer &&
                gameBoardCopy[0][2] == enemyPlayer) {
                    return enemyScore;
        } else if (gameBoardCopy[1][0] == enemyPlayer &&
                gameBoardCopy[1][1] == enemyPlayer &&
                gameBoardCopy[1][2] == enemyPlayer) {
                    return enemyScore;
        } else if (gameBoardCopy[2][0] == enemyPlayer &&
                gameBoardCopy[2][1] == enemyPlayer &&
                gameBoardCopy[2][2] == enemyPlayer) {
                    return enemyScore;
        }

        // check diagonals
        else if (gameBoardCopy[0][0] == ourPlayer &&
                gameBoardCopy[1][1] == ourPlayer &&
                gameBoardCopy[2][2] == ourPlayer) {
                    return ourScore;
        } else if (gameBoardCopy[0][2] == ourPlayer &&
                gameBoardCopy[1][1] == ourPlayer &&
                gameBoardCopy[2][0] == ourPlayer) {
                    return ourScore;
        }

        else if (gameBoardCopy[0][0] == enemyPlayer &&
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

    public double miniMax(char[][] gameBoardCopy, boolean isMaximizingPlayer) {

        // check to see if the game is in a terminal state
        int state = terminalState(gameBoardCopy);
        if (state == ourScore) {
            return (double)ourScore;
        } else if (state == enemyScore) {
            return (double)enemyScore;
        } else if (isMovesLeft(gameBoardCopy) == false) {
            return (double)0;
        }

        
        // if this is the maximizer's move
        if (isMaximizingPlayer) {
            double best = -1000;

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
                        best = Math.max(best, miniMax(gameBoardCopy, !isMaximizingPlayer));

                        // undo the move
                        gameBoardCopy[row][column] = boardValue;
                    }
                }
            }
            return best;
        }

        // if this is the minimizer's move
        else {
            double best = 1000;

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
                        best = Math.min(best, miniMax(gameBoardCopy, !isMaximizingPlayer)); 

                        // undo the move
                        gameBoardCopy[row][column] = boardValue;
                    }
                }
            }
            return best;
        }
    }

}
