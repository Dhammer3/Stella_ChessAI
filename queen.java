package chess;

public class queen extends Piece {

	Player play;
	int moveCount;

	public queen(Player play) {

		this.play = play;
		moveCount=0;
		
	}

	public String toString() {
		String k = "";
		if (this.getPlayer().toString().equals("White")) {
			k = "[*q*]";
		} else {
			k = "[!q!]";
		}
		return k;
	}

	public Player getPlayer() {
		return play;
	}
	// 1. determine if the current Player's king is in check (they are only allowed
	// to move king if that is true)
	// 2. determine the direction that the player is trying to move
	// 3. determine if the input is a legal move
	// 4. determine if there are any pieces in the way
	// 5. if passes all checks, piece should be moved.

	/*
	 * Quadrant Diagram -,- +,- [2][5][1] [7][Q][8] [3][6][4] -,+ +,+
	 * 
	 */

	public boolean move(Piece[][] board, int movePosX, int movePosY) {
		//Piece[][] board = new Piece[8][8];
		//gameBoard.copyBoard(board);

		int xPos = getX(this, board);

		int yPos = getY(this, board);

		int check1 = (Math.abs(xPos - movePosX));
		int check2 = (Math.abs(yPos - movePosY));

		int checkX = 0;
		int checkY = 0;
	
		/*
		if(((check1)!=0)||((check2)!=0)||(check1!=check2))
		{
			System.out.println("SSSSS");
			return false;
		}*/

		// if the white king is currently in check

		// quadrant 2 and 3
		// if the current xPos is greater than the movePosX then the player is trying to
		// go to either quad 2 or 3
		if (this.getX(this, board) > movePosX) {
			// quad 2
			if (this.getY(this, board) > movePosY) {
				if (check1 != check2) {

					return false;
				}

				if (piecesInWay(2, board, movePosX, movePosY) == false) {

					return true;
				}

			}

			// quad 3
			if (this.getY(this, board) < movePosY) {
				if (check1 != check2) {

					return false;
				}

				if (piecesInWay(3, board, movePosX, movePosY) == false) {

					return true;
				}
			}
		}
		// quadrant 1 and 4
		// if the current xPos is greater than the movePosX then the player is trying to
		// go to either quad 1 or 4
		if (this.getX(this, board) < movePosX) {
			// quad 1
			if (this.getY(this, board) > movePosY) {
				if (check1 != check2) {

					return false;
				}
				if (piecesInWay(1, board, movePosX, movePosY) == false) {

					return true;
				}

			}

			// quad 4
			if (this.getY(this, board) < movePosY) {
				if (check1 != check2) {

					return false;
				}
				if (piecesInWay(4, board, movePosX, movePosY) == false) {

					return true;
				}
			}
		}
		// quadrant 5 and 7
		// if the current xPos minus the movePosX equals 0 the player is trying to go to
		// either quad 5 or 7
		if (this.getX(this, board) - movePosX == 0) {

			// quad 3
			if (this.getY(this, board) > movePosY) {

				if (piecesInWay(5, board, movePosX, movePosY) == false) {

					return true;
				}

			}

			// quad 3
			if (this.getY(this, board) < movePosY) {

				if (piecesInWay(7, board, movePosX, movePosY) == false) {

					return true;
				}
			}
		}
		// quadrant 6 and 8
		// if the current yPos minus the movePosY then the player is trying to go to
		// either quad 6 or 8
		if (this.getY(this, board) - movePosY == 0) {

			// quad 2
			if (this.getX(this, board) > movePosX)
			{
				if(movePosY!=yPos)
				{
					return false;
				}
				if (piecesInWay(6, board, movePosX, movePosY) == false) {

					return true;
				}

			}

			// quad 4
			if (this.getX(this, board) < movePosX) {
				if (piecesInWay(8, board, movePosX, movePosY) == false) {

					return true;
				}
			}
		}

		return false;
	}

	// checks to see if there any pieces in the way in the direction the player is
	// trying to move
	// checks every spot along the path of movement, if there is a piece in the way,
	// returns false
	// if the player is trying to capture the enemy king, returns false
	/*
	 * Quadrant Diagram -,- +,- [2][5][1] [7][Q][8] [3][6][4] -,+ +,+
	 * 
	 */
	public boolean piecesInWay(int quadrant, Piece[][] board, int movePosX, int movePosY) {
		//Piece[][] board = new Piece[8][8];
		//gameBoard.copyBoard(board);
		int checkX = 0;
		int checkY = 0;

		int count = 0;

		int xPos = getX(this, board);

		int yPos = getY(this, board);

		int check1 = (Math.abs(xPos - movePosX));
		int check2 = (Math.abs(yPos - movePosY));
		
		if(this.getPlayer().toString().equals("White"))
		{
			if(board[movePosY][movePosX]!=null)
			{
				if(board[movePosY][movePosX].getPlayer().toString().equals("White"))
				{
					return true;
				}
			}
		}
		else
		{
			if(board[movePosY][movePosX]!=null)
			{
				if(board[movePosY][movePosX].getPlayer().toString().equals("Black"))
				{
					return true;
				}
			}
		}
		if((xPos==movePosX)&&(yPos==movePosY))
		{
			return false;
		}

		if (quadrant == 1) {

			checkX = this.getX(this, board) + 1;
			checkY = this.getY(this, board) - 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < check1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;

				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king

					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX < movePosX) {

						checkX++;
						checkY--;

					} 

				}
			}
		}
		if (quadrant == 2) {

	
			checkX = this.getX(this, board) - 1;
			checkY = this.getY(this, board) - 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < check1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {
						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX > movePosX) {
						checkX--;
						checkY--;
					} 
				}
			}
		}

		if (quadrant == 3) {

		
			checkX = this.getX(this, board) - 1;
			checkY = this.getY(this, board) + 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < check1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {
					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {
						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX > movePosX) {
						checkX--;
						checkY++;
					} else {
						return false;
					}
				}
			}
		}
		if (quadrant == 4) {

			checkX = this.getX(this, board) + 1;
			checkY = this.getY(this, board) + 1;

			// finish the other quadrants

			for (int i = 0; i < check1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {

						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {
						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX < movePosX) {

						checkX++;
						checkY++;

					} else {
						return false;
					}

				}
			}
		}

		//moving up
		if (quadrant == 5) {
			checkX = this.getX(this, board);
	
			for (int i = this.getY(this, board)-1; i >movePosY ; i--) 
			{	
				if(board[i][checkX]!=null)
				{
			
					return true;
				}
				
				
			}
		
				return false;
		}
	
		// moving left
		if (quadrant == 6) {

			checkX = this.getX(this, board);
			checkY = this.getY(this, board);
			
			for (int i = this.getX(this, board)-1; i > movePosX; i--) 
			{
				if (board[checkY][i] != null)
				{

					return true;
				}
				
			}
		
				return false;
		}
		// moving down
		if (quadrant == 7) {

	
			checkX = this.getX(this, board);
			checkY = this.getY(this, board);
			
			for (int i = checkY+1; i < movePosY; i++) 
			{
				if (board[i][checkX] != null) 
				{
					return true;
				}
				
			}
		
				return false;
		}
		// moving right
		if (quadrant == 8) {

			checkX = this.getX(this, board);
			checkY = this.getY(this, board);
			
			for (int i = checkX+1; i == movePosX; i++) 
			{
				if (board[checkY][i] != null)
				{
					return true;
				}
				
			}
		
				return false;
		}

		return true;

	}

	
}
