import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Game {
	int[][] board;
	HashMap letter = new HashMap<String,Integer>();
	private int x1,x2,y1,y2;
	int noumera;

	public void Game() throws IOException{
		board = new int[8][8];
		letter.put("a",1);
		letter.put("b",2);
		letter.put("c",3);
		letter.put("d",4);
		letter.put("e",5);
		letter.put("f",6);
		letter.put("g",7);
		letter.put("h",8);
		noumera =0;
		
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
		board[x1][y1] = 1;
		getFinallyHorsePosition();
		PossibleMoves(0, x1, y1);
		System.out.println("Minimum vimata: " + noumera);
	}

	private void getFinallyHorsePosition() throws IOException {
		
		
		String userInput = helpfunction();
		//divide into 2 variables
		String[] parts = userInput.split(" ");
		String row = parts[0];
		this.y2=Integer.parseInt(parts[1]);
		
		//place horse on requested position
		this.x2 = (Integer) letter.get(row);
		board[x2][y2] = 2;
		
		equalFinishedPosition(x1, y1, 0);
	}
	private boolean isValidPosition (int row, int column){
		if (row <1 || row > 8 ) return false;
		if (column <1 || column > 8 ) return false;
		return true;
	}

	public String helpfunction(){
		
		String row="";
		int column=0;
		String userInput="";
		do{
			System.out.println("getting user input");
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter the final position in xy notation where x = a to h and y = 1 to 8");
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
	
	boolean equalFinishedPosition(int x, int y, int counter){
		if((x==x2) && (y==y2)){
			System.out.println("Pige ston teliko proorismo! ");
			if (counter>noumera) noumera=counter;
			return true;
		}
		return false;
	}
	
	boolean PossibleMoves(int counter, int x, int y){
		if(!isValidPosition(x,y))return false;
		if(equalFinishedPosition(x,y, counter++)) return true;
		
		PossibleMoves(counter++, x+2, y+1);
		PossibleMoves(counter++, x+2, y-1);
		//PossibleMoves(counter++, x-2, y+1);
		//PossibleMoves(counter++, x-2, y-1);
		return false;
	}
    		
}
