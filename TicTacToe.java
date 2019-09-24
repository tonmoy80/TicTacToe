import java.io.IOException;
import java.util.Random;
import java.util.Scanner; 

public class TicTacToe {

	static String ticTacToeHLine = "===========";	
	static String ticTacToeA[][] = {{" 1 "," 2 "," 3 "},{" 4 "," 5 "," 6 "},{" 7 "," 8 "," 9 "}};
	static String whoStarts="";
	
	static String position = "";
	static String choice="";
	static int gameStatus=0;
	
	static int ticTacToeStatus[][]= {{0,0,0},{0,0,0},{0,0,0}};
	static int decisionArray[]= {0,1,2,3,4,5,6,7,8,9};
	
	public static void main(String[] args) throws IOException {
		initialMessage();
		startGame();
	}

	private static void initialMessage() {
		
		System.out.println("====This is Tic Tac Toe Console Game======"); 	
		System.out.println("Please input 1 or 2:"); 
		System.out.println("1) You - O"); 
		System.out.println("2) Machine - X");
		System.out.println("3) Exit"); 
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		whoStarts = choice;
	}

	public static void startGame() {
		
		int turn = 0;    
		String choice="";
		
        switch(whoStarts) {
        case "1":
        			// when human start first
        			do {
        				printTicTagToeA();
        				choice = humanPlay();
        				gameStatus = checkGameStatus(choice);
        				turn++;
        				if (turn==9 | gameStatus==1 | gameStatus==2) {
        					break;
        				}
        				
        				printTicTagToeA();
        				choice = machinePlay();
        				gameStatus = checkGameStatus(choice);        				
        				turn++;
        				if (turn==9 | gameStatus==1 | gameStatus==2) {
        					break;
        				}
        				
        			}while(true);
        break;
        case "2":
		        	// when machine start first
					do {
						printTicTagToeA();
						choice = machinePlay();
						gameStatus = checkGameStatus(choice);
						turn++;
						if (turn==9 | gameStatus==1 | gameStatus==2)
							break;
						
						printTicTagToeA();
						choice = humanPlay();
						gameStatus = checkGameStatus(choice);        				
						turn++;
						if (turn==9 | gameStatus==1 | gameStatus==2)
							break;
					
					}while(true);
		break;	        	
        case "3":
        			// code block
        			System.exit(0);
      }
      printTicTagToeA();
      if (gameStatus == 0)
    	  System.out.print("Game is draw !");
      if (gameStatus == 1)
    	  System.out.print("You win !");
      if (gameStatus == 2)
    	  System.out.print("Machine wins !");
	}
	
private static String humanPlay() {
		
	Scanner input = new Scanner(System.in);      
	do
	{
		System.out.println("Your turn:");	
		position = input.next();
		choice = input.next();
		if(decisionArray[Integer.valueOf(position).intValue()] == 0)
			System.out.println("This position already taken. Please input correct position:");
		else
			break;
	}while(true);	
	
	changeTicTagToeA(position,choice);
	decisionArray[Integer.valueOf(position).intValue()]=0;
	return choice;
	
}

//get position to win when machine has X-X-X chance
private static String getMachinePosition() {
	
	int fill=0;
	int block =0;
	int row = 0; 
	int col = 0;
	int pos = 0;
	// checking if machine can win when there is obvious chance
	for(row=0;row < 3;row++) {
		for(col=0;col < 3;col++) {		
			if(ticTacToeStatus[row][col]==2)
				fill++;
			
			else if(ticTacToeStatus[row][col]==1)
				block++;
			
			else if(ticTacToeStatus[row][col]==0)
				pos = col;
		}
		if (fill == 2 & ticTacToeStatus[row][pos]== 0)
				return ticTacToeA[row][pos].trim();
		if (block == 2 & ticTacToeStatus[row][pos]== 0)
				return ticTacToeA[row][pos].trim();
		
		pos = 0;
		fill = 0;
		block = 0;
	}
	for(col=0;col < 3;col++) {
		for(row=0;row < 3;row++) {		
			if(ticTacToeStatus[row][col]==2)
				fill++;
			else if(ticTacToeStatus[row][col]==1)
				block++;
			else if(ticTacToeStatus[row][col]==0)
				pos = row;
		}
		if (fill == 2 & ticTacToeStatus[pos][col]== 0)
				return ticTacToeA[pos][col].trim();
		if (block == 2 & ticTacToeStatus[pos][col]== 0)
			return ticTacToeA[pos][col].trim();
		
		pos = 0;
		fill = 0;
		block = 0;
	}	
	return Integer.toString(pos);
}


private static String machinePlay() {
	// TODO Auto-generated method stub
	String choice = "X";
	System.out.println("Machine is thinking...");
	
	// check which position machine can win;
	// if position value is other than 0, it means we get machine win position
	position = getMachinePosition();
	//System.out.println("Machine got position:"+position);
	if(Integer.valueOf(position).intValue()==0)
		//  it means we do not get machine win position, so select randomly
		position = getUniquePositionForMachine(position);
	
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Machine:"+position+" "+choice);
	changeTicTagToeA(position,choice);
	decisionArray[Integer.valueOf(position).intValue()]=0;   
	return choice;
}

private static String getUniquePositionForMachine(String position) {
		
	// apply system intelligence based on opponent moves
	// now just doing random choice
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
	
	for (int row = 0; row <ticTacToeA.length; row ++) {
	  
	    for (int col = 0; col < ticTacToeA.length; col++) {
	       
	    	if (ticTacToeA[row][col].equals(" "+move+" ")){
	        	ticTacToeA[row][col]= " "+choice+" ";
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
	
	//Human
	if (choice.equals("O")) {
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
	
	// machine
	if (choice.equals("X")) {
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
		
		if(ticTacToeStatus[0][0]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][2]==2)
			return 2;
		
		if(ticTacToeStatus[0][2]==2 & ticTacToeStatus[1][1]==2 & ticTacToeStatus[2][0]==2)
			return 2;
	}
	return 0;

}

}

