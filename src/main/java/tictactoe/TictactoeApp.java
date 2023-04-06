package tictactoe;

import java.util.HashSet;
import java.util.Scanner;

public class TictactoeApp {
	
	static HashSet<Integer> playerSet = new HashSet<Integer>();
	static HashSet<Integer> computerSet = new HashSet<Integer>();
	
	public static void main(String[] args) {
		char[][] gameboard = {
				{' ','|',' ','|',' '},
				{'-','|','-','|','-'},
				{' ','|',' ','|',' '},
				{'-','|','-','|','-'},
				{' ','|',' ','|',' '},
		};
		printGameboard(gameboard);
		Scanner input = new Scanner(System.in);
	
		while(true) {
			System.out.print("Choose a space and enter its value from 1-9:");
			int userPos = input.nextInt();
			while(playerSet.contains(userPos)||computerSet.contains(userPos)) {
				System.out.println();
				System.out.print("Position blocked. Enter another value from 1-9:");
				userPos = input.nextInt();
			}
			
			p_holder(gameboard, userPos, "Player");
			
			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			int computerPos = generateRandom();
			while(playerSet.contains(computerPos)||computerSet.contains(computerPos)) {
				computerPos = generateRandom();
			}
			
			p_holder(gameboard, computerPos, "Computer");
			
			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
	}
	
	static void printGameboard(char[][] gameboard) {
		for (int row = 0; row < gameboard.length; row++) {
			for (int column = 0; column < gameboard[0].length; column++) {
				System.out.print(gameboard[row][column]);
			}
			System.out.println();
		}
	}
	
	static void p_holder(char[][] gameboard, int pos, String user) {
		
		char gamepiece = 'X';
		if (user.equals("Player")) {
			gamepiece = 'X';
			playerSet.add(pos);
			System.out.println();

		} else if(user.equals("Computer")) {
			gamepiece = 'O';
			computerSet.add(pos);
			System.out.println();

		}
		
		switch(pos) {
		case 1: 
			gameboard[0][0] = gamepiece;
			break;
		case 2: 
			gameboard[0][2] = gamepiece;
			break;
		case 3: 
			gameboard[0][4] = gamepiece;
			break;
		case 4: 
			gameboard[2][0] = gamepiece;
			break;
		case 5: 
			gameboard[2][2] = gamepiece;
			break;
		case 6: 
			gameboard[2][4] = gamepiece;
			break;
		case 7: 
			gameboard[4][0] = gamepiece;
			break;
		case 8: 
			gameboard[4][2] = gamepiece;
			break;
		case 9: 
			gameboard[4][4] = gamepiece;
			break;
		default:
			System.out.println("Invalid input");
		}
		
		printGameboard(gameboard);

	}
	
	static String checkWinner() {
		//possible win conditions--three in a row of the same player marker
		HashSet<Integer> row1Win = new HashSet<Integer>();
		row1Win.add(1); row1Win.add(2); row1Win.add(3);
		
		HashSet<Integer> row2Win = new HashSet<Integer>();
		row2Win.add(4); row2Win.add(5); row2Win.add(6);
		
		HashSet<Integer> row3Win = new HashSet<Integer>();
		row3Win.add(7); row3Win.add(8); row3Win.add(9);
		
		HashSet<Integer> column1Win = new HashSet<Integer>();
		column1Win.add(1); column1Win.add(4); column1Win.add(7);
		
		HashSet<Integer> column2Win = new HashSet<Integer>();
		column2Win.add(2); column2Win.add(5); column2Win.add(8);
		
		HashSet<Integer> column3Win = new HashSet<Integer>();
		column3Win.add(3); column3Win.add(6); column3Win.add(9);
		
		HashSet<Integer> diag1Win = new HashSet<Integer>();
		diag1Win.add(1); diag1Win.add(5); diag1Win.add(9);
		
		HashSet<Integer> diag2Win = new HashSet<Integer>();
		diag2Win.add(3); diag2Win.add(5); diag2Win.add(7);

		HashSet<HashSet> set = new HashSet<HashSet>();
		//horizontal wins
		set.add(row1Win); 
		set.add(row2Win); 
		set.add(row3Win);
		//vertical wins
		set.add(column1Win); 
		set.add(column2Win); 
		set.add(column3Win);
		//diagonal wins
		set.add(diag1Win); 
		set.add(diag2Win);
		
		for (HashSet c : set) {
			if (playerSet.containsAll(c)) 
				return "You are the winner!";
			 else if (computerSet.containsAll(c)) 
				return "Computer wins!";
			 else 
				if (playerSet.size() + computerSet.size() == 9) 
					return "It's a draw!";
		}
		
		return "";
	}
	
	static int generateRandom() {
		int max = 9;
		int min = 1;
		int range = max - min + 1;
		int result = (int) (Math.random() * range) + min;
			return result;
	}
	


}
