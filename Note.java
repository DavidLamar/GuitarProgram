package GuitarProgram;

/**
 * Enum that holds all of the notes
 * 
 * @author David Lamar
 * @version 11/5/2015
 *
 */

public enum Note {
	A_FLAT, A, A_SHARP, 
	B_FLAT, B, B_SHARP,
	C_FLAT, C, C_SHARP,
	D_FLAT, D, D_SHARP,
	E_FLAT, E, E_SHARP,
	F_FLAT, F, F_SHARP,
	G_FLAT, G, G_SHARP;
	
	public static Note getAlternate(Note n){
		
		switch(n){
		case A_FLAT:
			return G_SHARP;
		case A:
			return A;
		case A_SHARP:
			return B_FLAT;
			
		case B_FLAT:
			return A_SHARP;
		case B:
			return C_FLAT;
		case B_SHARP:
			return C;
			
		case C_FLAT:
			return B;
		case C:
			return B_SHARP;
		case C_SHARP:
			return D_FLAT;
			
		case D_FLAT:
			return C_SHARP;
		case D:
			return D;
		case D_SHARP:
			return E_FLAT;
			
		case E_FLAT:
			return D_SHARP;
		case E:
			return F_FLAT;
		case E_SHARP:
			return F;
			
		case F_FLAT:
			return E;
		case F:
			return E_SHARP;
		case F_SHARP:
			return G_FLAT;
			
		case G_FLAT:
			return F_SHARP;
		case G:
			return G;
		case G_SHARP:
			return A_FLAT;
		default:
			return null;
		
		}

	}
}
