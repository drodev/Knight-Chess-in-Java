import java.io.IOException;



public class Start {
	public static void main(String[] args)  {
		
		Game val = new Game();
		try {
			val.Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");
	}
}
