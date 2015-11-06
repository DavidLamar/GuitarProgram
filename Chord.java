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
	
	//All the chords (add as you go):
	public static final Chord A = new Chord(new NoteArray(new Note[] {Note.A, Note.C_SHARP, Note.E}));
	public static final Chord B = new Chord(new NoteArray(new Note[] {Note.B, Note.D_SHARP, Note.F}));
	public static final Chord C = new Chord(new NoteArray(new Note[] {Note.C, Note.E, Note.G}));
	public static final Chord D = new Chord(new NoteArray(new Note[] {Note.D, Note.F_SHARP, Note.A}));
	public static final Chord E = new Chord(new NoteArray(new Note[] {Note.E, Note.G_SHARP, Note.B}));
	public static final Chord F = new Chord(new NoteArray(new Note[] {Note.F, Note.A, Note.C}));
	public static final Chord G = new Chord(new NoteArray(new Note[] {Note.G, Note.B, Note.D}));

	
	private NoteArray chord;
	
	
	public Chord(NoteArray chord){
		this.chord = chord;
	}
	
	public boolean contains(Note n){
		for(int i = 0; i < chord.length(); i++){
			if(chord.noteAt(i) == n || chord.noteAt(i) == Note.getAlternate(n)){
				return true;
			}
		}
		return false;
	}
}
