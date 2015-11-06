package GuitarProgram;

/**
 * Class holds an array of type Note; used for chords and the such
 * 
 * @author David Lamar
 * @version 11/5/2015
 *
 */

public class NoteArray {
	
	private Note[] notes;
	
	public NoteArray(Note[] n){
		notes = n;
	}
	
	public Note noteAt(int pos){
		return notes[pos];
	}
	
	public int indexOf(Note n){
		for(int i =0; i < notes.length; i++){
			if(n == notes[i]){
				return i;
			}
		}
		
		return -1;
	}
	
	public int length(){
		return notes.length;
	}
	
	public String toString(){
		String ret = "";
		
		for(int i = 0; i < notes.length; i++){
			ret += notes[i].name();
		}
		
		return ret;
	}
	
	
}
