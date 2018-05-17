package chess;

import java.util.ArrayList;
import java.util.*;
public class chess 
{

	Player white;
	Player black;
	moveList ml;
	

	//local history
	//research generics 
	
	public static void main (String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Piece [][] board= new Piece[8][8];
	
		board gameBoard=new board();
		gameBoard.copyBoard(board);
		Player white=new White();
		Player black=new Black();
		gameBoard.printBoard();
		
		
		while(true)
		{
		String temp="";
		System.out.println("Enter the coordinates for the piece to select ");
		temp=scan.next();
		
		String xPos1=temp.substring(0, 1);
		String yPos1=temp.substring(1);

		int xPos=locParserX(xPos1);
		int yPos=locParserY(yPos1);
	    System.out.println(gameBoard.getPiece(xPos, yPos)+gameBoard.getPiece(xPos, yPos).getPlayer().toString());

	    while(gameBoard.getPiece(xPos, yPos)==null)
	    {
	    	System.out.println("Enter the x pos, ex:'D' ");
			temp=scan.next();
			 xPos=locParserX(temp);
			 yPos=0;
			System.out.println("Enter the y pos, ex:'7' ");
			yPos=scan.nextInt();
		    System.out.println(gameBoard.getPiece(xPos, yPos)+gameBoard.getPiece(xPos, yPos).getPlayer().toString());
	    }
	    
	    System.out.println("Enter the coordinates for the move position");
		temp=scan.next();
		 xPos1=temp.substring(0, 1);
		 yPos1=temp.substring(1);
		 
		 int xMove=locParserX(xPos1);
		 int yMove=locParserY(yPos1);

	    if(gameBoard.getPiece(xPos, yPos).move(gameBoard, xMove, yMove)==true)
	    {
	    	board[yMove][xMove] = gameBoard.getPiece(xPos, yPos);
			board[yPos][xPos]=null;
			gameBoard.updateBoard(board);
	    }
	   
	    
	
	    gameBoard.printBoard();
		}

	}
	public static Piece[][] getpieceLoc( Piece [][] board, Piece p)
	{
		for(int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				if (board[i][j]==p)
				{
					return board;
				}
			}
		}
		return null;
	}
	public static int locParserX(String movePos)
	{
		//parsing the string
				String currentLocation=movePos.toUpperCase();
				int moveLocationY=0;
				String a="A";
				String [] move = { "A", "B", "C", "D", "E", "F", "G" ,"H"};
				for(int i=0; i<8; i++)
				{
					if (move[i].equals(currentLocation))
					{
						System.out.println(i);
						moveLocationY=i;
						return i;
					}
				}
				return -1;
	}
	public static int locParserY(String movePos)
	{	
		
				return Integer.parseInt(movePos)-1;
	}
}