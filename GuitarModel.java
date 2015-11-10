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
	}
	
	
	/**
	 * Gets all of the practice fingerings of the current chord
	 * 
	 * @return The fingerings
	 */
	public Fingering[] getFingerings(){
		
		boolean[] chordComplete = new boolean[strings.length];
		
		Fingering temp  = new Fingering(strings.length, fretNumber);
		
		Fingering[] fingerings = new Fingering[10];
		
		int noteNumber = 0;
		int fretCount = 0;
		int chordNumber = 0;
		boolean sameCheck = false;
		boolean complete = false;
		boolean barCheck = false;
		
		
		for(int fret = 0; fret < fretNumber; fret++){ //Go for the number of frets
			fretCount ++;
			sameCheck = false; //reset sameCheck because we're no longer on the same fret
			
			if(!allFalse(chordComplete)){
				barCheck = true;
			}
			
			//Makes the fingering
			for(int str = 0; str < strings.length; str++){ //Go for the number of strings
				if(fret > fretNumber - 1){
					break;
				}
				
				if(stringValues[str][fret]){ //Check if current string, current fret is true
					if(!chordComplete[str]){ //check if chord is already complete here
						
						
						temp.setPos(str, fret, true); //Adds a true to the current temp
						
						if(!sameCheck || barCheck){ //Checks to see if this fret already has other 
							noteNumber++; //Increase number of notes in the chord
						}
		
						chordComplete[str] = true; //Chord is now complete at this string
						sameCheck = true;
					}
				}
			}
			
			//Does a reset
			if(noteNumber > 4 || fretCount > 4){
				
				//Resets temp so that it can be reused.
				temp  = new Fingering(strings.length, fretNumber);
				
				chordComplete = new boolean[strings.length];
				
				sameCheck = false; 
				noteNumber = 0;
				fret -= (fretCount - 1); // sets fret back
				fretCount = 0;
				complete = false;
				barCheck = false;
			}
			
			//Checks if the entire array is true
			complete = allTrue(chordComplete);

			
			if(complete){ //Checks if the chord is complete and returns a finished chord
				
				chordComplete = new boolean[strings.length];
				
				//Adds the current chord to the return variable
				if(chordNumber < fingerings.length){
					fingerings[chordNumber] = temp;
					chordNumber ++;
				}
				
				
				//Resets temp so that it can be reused.
				temp  = new Fingering(strings.length, fretNumber);
				
				
				sameCheck = false; 
				noteNumber = 0;
				fret -= (fretCount - 1); // sets fret back
				fretCount = 0;
				complete = false;
				barCheck = false;
			}
		
			
		}
		
		
		return fingerings;
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
	
	private boolean allTrue(boolean[] arr){
		for(boolean b : arr) if(!b) return false;
		return true;
	}
	
	private boolean allFalse(boolean[] arr){
		for(boolean b : arr) if(b) return false;
		return true;
	}
	
}
