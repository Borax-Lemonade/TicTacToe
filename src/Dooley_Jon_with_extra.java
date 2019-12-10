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

        // TODO: algorithm

        // 1st: see if the other player has already made a move (they moved first)
        // and if they have get the coordinates of the past move
        for (int row = 0; row < gameBoard.length; row++) {
        	for (int column = 0; column < gameBoard[row].length; column++) {
        		if (gameBoard[row][column] == otherTeam) {
        			System.out.println("The other player has already made a move!");
        			formerMove = true;
        			formerMoveRow = row;
        			formerMoveColumn = column;
        		}
        	}
        }


        // 2nd: if not, put our move in the top right corner, but if so,
        //      if the former move is a corner, put our move in the center.
        //      if the former move is a center, put our move next to it.
        //      if the former move is in the center row or column but not in the center, put our move in the center.
        if(!formerMove) {
        	nextMoveRow = 0;
        	nextMoveColumn = 2;
        }
        else {
          if((formerMoveRow == 0 && ((formerMoveColumn ==  0) || formerMoveColumn == 2)) || (formerMoveRow == 2 && ((formerMoveColumn ==  0) || formerMoveColumn == 2))) {
            nextMoveRow = 1;
            nextMoveColumn = 1;
          }
          if(formerMoveRow == 1 && formerMoveColumn = 1) {
            nextMoveRow = //welp
            nextMoveColumn = //welp again
          }

        }

        // 3rd: submit move
        submitMove(nextMoveRow, nextMoveColumn);

    }

}
