import java.util.Dictionary;
import java.util.Map;

import jdk.jfr.Unsigned;

public class Dooley_Jon extends Player {

    public void calcMove() {
    	boolean formerMove = false;
    	int formerMoveRow, formerMoveColumn;
    	int nextMoveRow = 0, nextMoveColumn = 0;
    	
        char[][] gameBoard = getGameState();
        char myTeam = getTeam();
        char otherTeam;
        if (myTeam == 'X') {
        	otherTeam = 'O';
        } else {
        	otherTeam = 'X';
        }
        
        // Implement Minimax algorithm
        int ourScore = 10;
        int enemyScore = -10;
        int tie = 0;
        
        submitMove(nextMoveRow, nextMoveColumn);

    }


}