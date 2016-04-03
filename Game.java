import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Game {
	int[][] board;
	HashMap letter = new HashMap<String,Integer>();
	private int x1,x2,y1,y2;
	int noumera;
	String way;
	String meta;
	
	public void Game() throws IOException{
		board = new int[8][8];
		letter.put("A",0);
		letter.put("B",1);
		letter.put("C",2);
		letter.put("D",3);
		letter.put("E",4);
		letter.put("F",5);
		letter.put("G",6);
		letter.put("H",7);
		
		letter.put(0,"A");
		letter.put(1,"B");
		letter.put(2,"C");
		letter.put(3,"D");
		letter.put(4,"E");
		letter.put(5,"F");
		letter.put(6,"G");
		letter.put(7,"H");
		noumera =1000;
		way="";
		meta="";
		initBoard();
		getInitialHorsePosition();
		
		
	};

	// init the board first
	private void initBoard() {
		System.out.println("init board()");
		for(int x=0; x<8; x++){
			for(int y=0; y<8; y++){
				board[x][y] = 0;
			}
		}		
	}
	
	private void getInitialHorsePosition() throws IOException {
		
		String userInput = helpfunction();
		//divide into 2 variables
		String[] parts = userInput.split(" ");
		String row = parts[0];
		this.y1=Integer.parseInt(parts[1]);
		//place horse on requested position
		this.x1 = (Integer) letter.get(row);
		board[x1][y1] = 5;
		getFinallyHorsePosition();
		PossibleMoves("",0, x1, y1);
		System.out.println("Steps: " + noumera);
		System.out.println("Way: " + way);
	}

	private void getFinallyHorsePosition() throws IOException {
		
		
		String userInput = helpfunction();
		//divide into 2 variables
		String[] parts = userInput.split(" ");
		String row = parts[0];
		this.y2=Integer.parseInt(parts[1]);
		
		//place horse on requested position
		this.x2 = (Integer) letter.get(row);
		
		equalFinishedPosition("is already there",x1, y1, 0);
	}
	private boolean isValidPosition (int row, int column){
		if (row <0 || row > 7 ) return false;
		if (column <0 || column > 7 ) return false;
		return true;
	}

	public String helpfunction(){
		
		String row="";
		int column=0;
		String userInput="";
		do{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter the final position in xy notation where x = A to H and y = 1 to 8");
			try {
				userInput  = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//divide into 2 variables
			String[] parts = userInput.split(" ");
			row = parts[0];
			column=Integer.parseInt(parts[1]);
			
		} while(!isValidPosition((Integer) letter.get(row), column));
		
		return userInput;
	}
	
	boolean equalFinishedPosition(String w, int x, int y, int counter){
		if((x==x2) && (y==y2)){
			
			if (counter<noumera) {
			   noumera=counter;
			   meta=(String) letter.get(x);
			   w+=" " +meta+ "" +y;
			   way=w;
			}
			return true;
		}
		return false;
	}
	

	boolean PossibleMoves(String w, int counter, int x, int y){
		
		if(!isValidPosition(x,y))return false;
		if (board[x][y] == 1) return false; //exei idi patisei 
		board[x][y] = 1; //pataei 
		counter++;
		if(equalFinishedPosition(w,x,y, counter)) return true;
		
		meta=(String) letter.get(x);
		   
		w+=" " +meta+ "" +y+" =>";
		  
		
		PossibleMoves(w, counter, x+2, y+1);
		PossibleMoves(w, counter, x+2, y-1);
		PossibleMoves(w, counter, x-2, y+1);
		PossibleMoves(w, counter, x-2, y-1);
		
		PossibleMoves(w, counter, x+1, y+2);
		PossibleMoves(w, counter, x-1, y+2);
		PossibleMoves(w, counter, x+1, y-2);
		PossibleMoves(w, counter, x-1, y-2);
		return false;
	}
    		
}
