package GuitarProgram;

public class Fingering {
	private boolean[][] board;
	
	public Fingering(int strings, int frets){
		
		board = new boolean[strings][frets];
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = false;
			}
		}
	}
	
	public void setPos(int string, int fret, boolean value){
		board[string][fret] = value;
	}
	
	public boolean getPos(int string, int fret){
		return board[string][fret];
	}
	
	public String toString(){
		String out = "";
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j ++){
				if(board[i][j] == true){
					out += "__0__|";
				}else{
					out += "_____|";
				}
			}
			out += "\n";
		}
		
		return out;
	}
}
