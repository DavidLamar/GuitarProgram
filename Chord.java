package GuitarProgram;


/**
 * Class that is a specific kind of NoteArray; it's more structured,
 * however, than the NoteArray class.
 * 
 * @author David Lamar
 * @version 11/5/2015
 *
 */

public class Chord {
	
	private NoteArray chord;
	private String name;
	
	
	public Chord(NoteArray chord, String name){
		this.chord = chord;
		this.name = name;
	}
	
	public boolean contains(Note n){
		for(int i = 0; i < chord.length(); i++){
			if(chord.noteAt(i) == n || chord.noteAt(i) == Note.getAlternate(n)){
				return true;
			}
		}
		return false;
	}
	
	public String getName(){
		return name;
	}
}
