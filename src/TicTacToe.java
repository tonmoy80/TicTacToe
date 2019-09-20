import java.io.IOException;
import java.util.Random;
import java.util.Scanner; 

public class TicTacToe {

	static String ticTacToeHLine = "===========";
	
	static String ticTacToeA[][] = {{" 1 "," 2 "," 3 "},{" 4 "," 5 "," 6 "},{" 7 "," 8 "," 9 "}};
	
	static int ticTacToeStatus[][]= {{0,0,0},{0,0,0},{0,0,0}};
	
	static int roboticDecisionArray[]= {0,1,2,3,4,5,6,7,8,9};
	
	public static void main(String[] args) throws IOException {

		printTicTagToeA();		
		startGame();
	
	}

	public static void startGame() {
		
		String position = "";
		String choice="";
		int gameStatus=0;
		
		Scanner input = new Scanner(System.in);
		
        int[] player = {1,2,1,2,1,2,1,2,1};
        int turn = 0;
              
        // 
        
        do {			
        
			// Human Player Input
			if (player[turn]==1) {
				
				System.out.print("Player"+ player[turn]+":");
				position = input.next();
				choice = input.next();
			}
			
			// System/CPU Input
			if (player[turn]==2) {
				
				position = SystemReplyPosition(position);
				choice = SystemReplyChoice(choice);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.print("Player"+ player[turn]+":"+position+" "+choice);
			}
			
			changeTicTagToeA(position,choice);
			printTicTagToeA();
			
			roboticDecisionArray[Integer.valueOf(position).intValue()]=0;
			
			//check if game is draw/win/loss
			gameStatus = checkGameStatus(choice);
			
			turn++;
			
			if (turn==9 | gameStatus==1 | gameStatus==2) {
				break;
			}
			
		}while(true);
        
        if(gameStatus == 1) {
        	System.out.println("X wins!"); 
        }
        if(gameStatus == 2) {
        	System.out.println("O wins!"); 
        }
        
        if(gameStatus == 2) {
        	System.out.println("Game Draw!"); 
        }
        
	}
	
private static String SystemReplyChoice(String choice) {
		
	if (choice.equals("X")) {
		return "O";
	}
	else return "X";
	
}

private static String SystemReplyPosition(String position) {
		
	// apply system intelligence based on opponent moves
	// now just doing random choice
	
	Random random = new Random();
	int pos=0;
	
	pos = random.nextInt(10);
	
	// check if this moves already taken
	while(roboticDecisionArray[pos]==0) {
		
		pos = random.nextInt(10);
	}
	// return robot's move
	return Integer.toString(pos);
}

public static void changeTicTagToeA(String move, String choice) {
	
	for (int row = 0; row <ticTacToeA.length; row ++) {
	  
	    for (int col = 0; col < ticTacToeA.length; col++) {
	       
	    	if (ticTacToeA[row][col].equals(" "+move+" ")){
	        	ticTacToeA[row][col]= " "+choice+" ";
	        	if(choice.equals("X")) {
		    		ticTacToeStatus[row][col]=1;
		    	}
		    	
		    	if(choice.equals("O")) {
		    		ticTacToeStatus[row][col]=2;
		    	}
	    	}
	        
	    }
	    System.out.println();
	}
}
public static void printTicTagToeA(){
	
	System.out.println(ticTacToeHLine);
	for (int row = 0; row <ticTacToeA.length; row ++) {
	  
	    for (int col = 0; col < ticTacToeA.length; col++) {
	       
	        System.out.print(ticTacToeA[row][col]);
	        System.out.print("|");
	        
	    }
	    System.out.println("");
	    System.out.println(ticTacToeHLine);
	    
	}
}

public static int checkGameStatus(String choice){
	
	if (choice.equals("X")) {
		if(ticTacToeStatus[0][0]==1 & ticTacToeStatus[0][1]==1 & ticTacToeStatus[0][2]==1)
			return 1;
		
		if(ticTacToeStatus[1][0]==1 & ticTacToeStatus[1][1]==1 & ticTacToeStatus[1][2]==1)
			return 1;
		
		if(ticTacToeStatus[2][0]==1 & ticTacToeStatus[2][1]==1 & ticTacToeStatus[2][2]==1)
			return 1;
		
		if(ticTacToeStatus[0][0]==1 & ticTacToeStatus[1][0]==1 & ticTacToeStatus[2][0]==1)
			return 1;
		
		if(ticTacToeStatus[0][1]==1 & ticTacToeStatus[1][1]==1 & ticTacToeStatus[2][1]==1)
			return 1;
		
		if(ticTacToeStatus[0][2]==1 & ticTacToeStatus[1][2]==1 & ticTacToeStatus[2][2]==1)
			return 1;
		
		if(ticTacToeStatus[0][0]==1 & ticTacToeStatus[1][1]==1 & ticTacToeStatus[2][2]==1)
			return 1;
		
		if(ticTacToeStatus[0][2]==1 & ticTacToeStatus[1][1]==1 & ticTacToeStatus[2][0]==1)
			return 1;
	}
	
	if (choice.equals("O")) {
		if(ticTacToeStatus[0][0]==2 & ticTacToeStatus[0][1]==2 & ticTacToeStatus[0][2]==2)
			return 2;
		
		if(ticTacToeStatus[1][0]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[1][2]==2)
			return 2;
		
		if(ticTacToeStatus[2][0]==2 & ticTacToeStatus[2][1]==2 & ticTacToeStatus[2][2]==2)
			return 2;
		
		if(ticTacToeStatus[0][0]==2 & ticTacToeStatus[1][0]==2 & ticTacToeStatus[2][0]==2)
			return 2;
		
		if(ticTacToeStatus[0][1]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][1]==2)
			return 2;
		
		if(ticTacToeStatus[0][2]==2 & ticTacToeStatus[1][2]==2 & ticTacToeStatus[2][2]==2)
			return 2;
		
		if(ticTacToeStatus[0][0]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][2]==1)
			return 2;
		
		if(ticTacToeStatus[0][2]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][0]==2)
			return 2;
	}
	return 0;

}

}

