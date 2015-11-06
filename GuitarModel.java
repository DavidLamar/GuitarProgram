package GuitarProgram;


/**
 * Class that does all of the computation
 * 
 * @author David Lamar
 * @version 11/5/2015
 *
 */

public class GuitarModel {
	
	
	/* Standard tuning, E B G D A E */
	public final static NoteArray STANDARD = new NoteArray(new Note[] {Note.E, Note.B, Note.G, Note.D, Note.A, Note.E});
	
	/* Drop-D tuning, D B G D A E */
	public final static NoteArray DROP_D = new NoteArray(new Note[] {Note.D, Note.B, Note.G, Note.D, Note.A, Note.E});

	
	/* Holds notes in a sequential order so we can manipulate them easier */
	private final static NoteArray NOTE = new NoteArray(new Note[] {Note.A, Note.A_SHARP, Note.B, Note.C, Note.C_SHARP, Note.D, Note.D_SHARP, Note.E, Note.F, Note.F_SHARP, Note.G, Note.G_SHARP});
	
	/* Holds values for the guitar strings and the notes on each fret*/
	private NoteArray[] strings;
	
	/* A boolean array that outputs true if a given fret on a given string is part of the chord */
	private boolean[][] stringValues;
	
	/* All of the practical fingerings for a given chord */
	private boolean[][][] fingerings;
	
	/* The current chord being checked */
	private Chord chord;
	
	/* Number of frets the guitar has */
	private int fretNumber;
	
	/*
	 * For the Fingering Algorithm: Go for each fret (max of 5) and
	 * if every fret is covered by a note with only 4 fingers on
	 * different bars (So that this takes into account bar chords)
	 * return the fingering (It'll just be the truth table array)
	 */

	/**
	 * Constructor that sets the number of frets, the tuning, and the
	 * number of strings for the guitar. It also assigns note values
	 * to each string based on the tuning.
	 * 
	 * 
	 * @param fretNumber The number of frets the guitar has
	 * @param tuning The tuning of the guitar
	 * @param NumStrings The number of strings the guitar has
	 */
	public GuitarModel(int fretNumber, NoteArray tuning, int NumStrings){
		
		strings = new NoteArray[NumStrings];
		
		this.fretNumber = fretNumber;
		
		Note[] temp = new Note[fretNumber];
		
		//Goes for the number of strings
		for(int i = 0; i < strings.length; i++){
			//Goes for the number of frets
			for(int j = 0; j < fretNumber; j++){
				//Gets the note, from NOTE at the position of the tuning note for the given string
				temp[j] = NOTE.noteAt((NOTE.indexOf(tuning.noteAt(i)) + j) % 12);
			}

			strings[i] = new NoteArray(temp);
			
			temp = new Note[fretNumber];
		}
	}
	
	
	/**
	 * Gets the current chord being used
	 * 
	 * @return chord
	 */
	public Chord getChord(){
		return chord;
	}
	
	
	/**
	 * Sets the chord to be used
	 * 
	 * @param c The new chord
	 */
	public void setChord(Chord c){
		chord = c;
		
		stringValues = new boolean[strings.length][fretNumber];
		
		//Go for the number of strings
		for(int i = 0; i < strings.length; i++){
			//Go for the number of frets
			for(int j = 0; j < fretNumber; j++){
				//Check if the current note is inside the current chord
				//Return true if it, or its alternate is. Return false
				//otherwise
				if(chord.contains( strings[i].noteAt(j))){
					stringValues[i][j] = true;
				}else{
					stringValues[i][j] = false;
				}
			}
		}
		//Gives us our first value of the fingerings; it's just all the notes
		//fingerings[0] = stringValues; 
	}
	
	
	/**
	 * Gets all of the practice fingerings of the current chord
	 * 
	 * @return The fingerings
	 */
	public boolean[][][] getFingerings(){
		for(){
			for(){
				
			}
		}
		
		return null;
	}
	
	
	/**
	 * Gets the note on string, string and fret, index
	 * 
	 * @param string The string we're looking at
	 * @param index The fret of the string
	 * 
	 * @return The note at the position
	 */
	public Note getNoteAt(int string, int index){
		return strings[string].noteAt(index);
	}
	
	/**
	 * Gets the fret board with the true values in the position of
	 * the notes that are in the given chord
	 * 
	 * @return The fret board
	 */
	public boolean[][] getFretBoard(){
		return stringValues;
	}
	
}
