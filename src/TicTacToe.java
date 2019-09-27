/*
 * Simple TicTacToe Game - Command Line Based
 * Version1.0
 * Author: tonmoy.paul@bjitgroup.com
 * 
 */
import java.io.IOException;
import java.util.Random;
import java.util.Scanner; 

public class TicTacToe {

	static String ticTacToe[][] = {{" 1 "," 2 "," 3 "},{" 4 "," 5 "," 6 "},{" 7 "," 8 "," 9 "}};
	static String position = "";
	static String choice="";
	static int gameStatus=0;
	static int ticTacToeStatus[][]= {{0,0,0},{0,0,0},{0,0,0}};
	static int decisionArray[]= {0,1,2,3,4,5,6,7,8,9};
	static int moves[]= {1,2,1,2,1,2,1,2,1,2};
		
	static int count=0;
	static Scanner input=new Scanner(System.in);	
	public static void main(String[] args) throws IOException {
		do {		
			startGame();
			System.out.println("Do you want to continue? (Y/N):");
			String choice = input.next();
			if(choice.equals("Y")) continue;
			else if (choice.equals("N")) {
				System.out.println("Game exits! Thank you for playing!!");
				break;
			}
			else System.out.println("Error: Please input correct option.");
		}while(true);
		input.close();
	}
	private static void initVariables() {
		int pos=1;
	    position = "";
		choice="";
		gameStatus=0;
		for(int row=0;row < 3;row++) {
			for(int col=0;col < 3;col++) {	
				ticTacToeStatus[row][col]=0;
				ticTacToe[row][col]= " "+pos+" ";
				pos++;
			}
		}
		for(int row=0;row < 10;row++)
			decisionArray[row]= row;
		count=0;
	}
	private static String showMenu() {
		System.out.println("====This is Tic Tac Toe Console Game======");
		System.out.println("|   How to play                           |");
		System.out.println("|   Human:1 O (capital letter O)          |");
		System.out.println("|   Machine: Automatic                    |");
		System.out.println("=========================================="); 	
		System.out.println("Please input 1 or 2:"); 
		System.out.println("1) You - O"); 
		System.out.println("2) Machine - X");
		System.out.println("3) Exit"); 
		String choice = input.next();
		return choice;
	}
	public static void startGame() {
		
		int turn = 0;    
		String choice="";
		String whoStarts="";
		
		initVariables();
		whoStarts=showMenu();
		
        switch(whoStarts) {
        case "1":
        			// when human start first
        			 turn = 0;
        			 do {
        				printTicTagToeA();
        				choice = play(moves[turn]);
        				gameStatus = checkGameStatus(choice);
        				turn++;
        				if (turn==9 | gameStatus==1 | gameStatus==2)
        					break;
        			}while(true);
        break;
        case "2":
		        	// when machine start first
        	 		turn = 1;
        			do {
						printTicTagToeA();
        				choice = play(moves[turn]);
        				gameStatus = checkGameStatus(choice);
        				turn++;
        				if (turn==10 | gameStatus==1 | gameStatus==2)
        					break;					
					}while(true);
		break;	        	
        case "3":
        	System.out.println("Game exits!");		
        	System.exit(0);
      }
      printTicTagToeA();
      if (gameStatus == 0)
    	  System.out.println("Game is draw !");
      if (gameStatus == 1)
    	  System.out.println("You win !");
      if (gameStatus == 2)
    	  System.out.println("Machine wins !");
	}

private static String play(int mode) {
	
	if(mode==1)
		choice = humanPlay();
	if(mode==2)
		choice = machinePlay();
	return choice;
}
private static String humanPlay() {
		
	do
	{
		System.out.println("Your turn:");	
		position = input.next();
		choice = input.next();
		if(!choice.equals("O")) {
			System.out.println("Please input correctly.");
			System.out.println("Your choice is: O, Machine choice is:X");
		}
		else if(decisionArray[Integer.valueOf(position).intValue()] == 0)
			System.out.println("This position already taken. Please input correct position:");
		
		else break;
	}while(true);	
	
	changeTicTagToeA(position,choice);
	decisionArray[Integer.valueOf(position).intValue()]=0;
	return choice;
}

private static String machinePlay() {
	
	String choice = "X";
	System.out.println("Machine is thinking...");
	
	// firstly check when machine can win. This function returns value between 1-9
	//position = getMachineWinPosition();
	position = getMachinePositionTest(2);
	if(Integer.valueOf(position).intValue()==0) {
		// secondly check when machine can block opponent - O-O-X
		position = getMachinePositionTest(1);
		if(Integer.valueOf(position).intValue()==0)
			//  it means we don't get win or block situation. chose random
			position = getUniquePositionForMachine(position);
	}	
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("Machine:"+position+" "+choice);
	changeTicTagToeA(position,choice);
	decisionArray[Integer.valueOf(position).intValue()]=0;   
	return choice;
}

private static String getUniquePositionForMachine(String position) {		
	// apply system intelligence based on opponent moves
	Random random = new Random();
	int pos=0;
	pos = random.nextInt(10);
	// check if this moves already taken
	while(decisionArray[pos]==0)
		pos = random.nextInt(10);	
	// return machine move
	return Integer.toString(pos);
}
public static void changeTicTagToeA(String move, String choice) {
	
	for (int row = 0; row <ticTacToe.length; row ++) {
	  for (int col = 0; col < ticTacToe.length; col++) {
	       if (ticTacToe[row][col].equals(" "+move+" ")){
	        	ticTacToe[row][col]= " "+choice+" ";
	        	if(choice.equals("O"))
		    		// Human
	        		ticTacToeStatus[row][col]=1;
		    	if(choice.equals("X"))
		    		//machine
		    		ticTacToeStatus[row][col]=2;
		    }
	    }
	    System.out.println();
	}
}

public static void printTicTagToeA(){
	System.out.println("===========");
	for (int row = 0; row <ticTacToe.length; row ++) {
	  for (int col = 0; col < ticTacToe.length; col++) {
	        System.out.print(ticTacToe[row][col]);
	        System.out.print("|");
	    }
	    System.out.println("");
	    System.out.println("===========");
	}
}

public static int checkGameStatus(String choice){
	//Human
	if (choice.equals("O")) {
		for (int row = 0; row <ticTacToeStatus.length; row ++)
			 if(ticTacToeStatus[row][0]==1 & ticTacToeStatus[row][1]==1 & ticTacToeStatus[row][2]==1)
				 return 1;
		for (int col = 0; col <ticTacToeStatus.length; col++)
			 if(ticTacToeStatus[0][col]==1 & ticTacToeStatus[1][col]==1 & ticTacToeStatus[2][col]==1)
				 return 1;
		if((ticTacToeStatus[0][0]==1 & ticTacToeStatus[1][1]==1 & ticTacToeStatus[2][2]==1)|
				(ticTacToeStatus[0][2]==1 & ticTacToeStatus[1][1]==1 & ticTacToeStatus[2][0]==1))
			return 1;
	}	
	// machine
	if (choice.equals("X")) {
		for (int row = 0; row <ticTacToeStatus.length; row ++)
			 if(ticTacToeStatus[row][0]==2 & ticTacToeStatus[row][1]==2 & ticTacToeStatus[row][2]==2)
				 return 2;
		for (int col = 0; col <ticTacToeStatus.length; col++)
			 if(ticTacToeStatus[0][col]==2 & ticTacToeStatus[1][col]==2 & ticTacToeStatus[2][col]==2)
				 return 2;
		if((ticTacToeStatus[0][0]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][2]==2)|
				(ticTacToeStatus[0][2]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][0]==2))
			return 2;
	}
	return 0;
}
//get position to win when machine has X-X-X chance
private static String getMachinePositionTest(int player) {
	
	int row = 0; 
	int col = 0;
	int pos = 0;
	// checking if machine can win when there is obvious chance
	//row check
	for(row=0;row < 3;row++) {
		for(col=0;col < 3;col++) {		
			if(ticTacToeStatus[row][col]==player)
				count++;
			else if(ticTacToeStatus[row][col]==0)
				pos = col;
		}
		if (count == 2 & ticTacToeStatus[row][pos]== 0)
				return ticTacToe[row][pos].trim();
		pos = 0;
		count = 0;
	}
	//column check
	for(col=0;col < 3;col++) {
		for(row=0;row < 3;row++) {		
			if(ticTacToeStatus[row][col]==player)
				count++;
			else if(ticTacToeStatus[row][col]==0)
				pos = row;
		}
		if (count == 2 & ticTacToeStatus[pos][col]== 0)
				return ticTacToe[pos][col].trim();
		pos = 0;
		count = 0;
	}
	return Integer.toString(pos);
}

}

