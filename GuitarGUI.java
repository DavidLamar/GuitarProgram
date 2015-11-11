package GuitarProgram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class that displays the GUI
 * 
 * @author David Lamar
 * @version 11/5/2015
 *
 */

public class GuitarGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	private static ArrayList<Chord> CHORDS = new ArrayList<Chord>();
	
	static JPanel programPanel;
	static JMenuBar fileBar;
	
	GuitarModel gModel;
	
	private JMenu file, edit, about;
	private JMenuItem exit;
	private JMenuItem addChord, changeStrings, changeTuning, preferences;
	private JMenuItem version;
	
	private JComboBox chordList;
	
	private int stringNum = 6;
	private int fretNum = 20;
	private NoteArray tuning = GuitarModel.STANDARD;
	
	private JLabel[][] strings;
	private JButton changeFingering;
	
	private Fingering[] fingerings;
	private int currentFingering = 0;
	
	
	public static void main(String[] args){
		GuitarGUI guitar = new GuitarGUI();
		
		guitar.setDefaultCloseOperation(EXIT_ON_CLOSE);
		guitar.setTitle("Guitar Program");
		
		guitar.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		guitar.setJMenuBar(fileBar);
		
		guitar.add(programPanel);
		
		guitar.setResizable(false);
		guitar.pack();
		guitar.setVisible(true);
	}
	
	
	public GuitarGUI(){

		fileBar = new JMenuBar();
		initializeChords();
		
		gModel = new GuitarModel(fretNum, tuning, stringNum);
		gModel.setChord(CHORDS.get(1));
		fingerings = gModel.getFingerings();

		FileListener listener = new FileListener();
		
		//File stuff
		file = new JMenu("File");
		fileBar.add(file);
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(listener);
		file.add(exit);
		
		
		//Edit stuff
		edit = new JMenu("Edit");
		fileBar.add(edit);
		
		addChord = new JMenuItem("Add Chord");
		addChord.addActionListener(listener);
		edit.add(addChord);
		
		changeStrings = new JMenuItem("Change Number of Strings");
		changeStrings.addActionListener(listener);
		edit.add(changeStrings);
		
		changeTuning = new JMenuItem("Change Tuning");
		changeTuning.addActionListener(listener);
		edit.add(changeTuning);
		
		edit.addSeparator();
		
		preferences = new JMenuItem("Preferences");
		preferences.addActionListener(listener);
		edit.add(preferences);
		
		//About stuff
		
		about = new JMenu("About");
		fileBar.add(about);
		
		version = new JMenuItem("Version");
		version.addActionListener(listener);
		about.add(version);
		
		initializePanel(listener);
		
	}
	
	private void initializePanel(ActionListener listener){
		programPanel = new JPanel();
		programPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		JPanel fretBoard = new JPanel();
		fretBoard.setPreferredSize(new Dimension(WIDTH, 200));
		fretBoard.setLayout(new GridLayout(stringNum, fretNum));
		
		JPanel blank = new JPanel();
		blank.setPreferredSize(new Dimension(WIDTH, 300));
		
		String[] cList = new String[CHORDS.size()];
		for(int i = 0; i < CHORDS.size(); i++){
			cList[i] = CHORDS.get(i).getName();
		}
		
		chordList = new JComboBox(cList);
		chordList.setSelectedItem(0);
		chordList.addActionListener(listener);
		
		changeFingering = new JButton("Next Fingering >");
		changeFingering.addActionListener(listener);
		
		topPanel.add(chordList);
		topPanel.add(changeFingering);
		
		programPanel.add(topPanel, BorderLayout.NORTH);
		
		strings = new JLabel[stringNum][fretNum];
		
		for(int str = 0; str < stringNum; str++){
			for(int fret = 0; fret < fretNum - 1; fret++){
				
				if( fingerings[0].getPos(str, fret) ){
					strings[str][fret] = new JLabel("_--_|");
				}else{
					strings[str][fret] = new JLabel("____|");
				}
				
				
				
				strings[str][fret].setOpaque(true);
				fretBoard.add(strings[str][fret]);
			}
		}
		
		
		programPanel.add(fretBoard, BorderLayout.LINE_START);
		programPanel.add(blank, BorderLayout.SOUTH);

		
		
	}
	
	public void initializeChords(){
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.A_FLAT, Note.C, Note.E_FLAT}), "A Flat"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.A, Note.C_SHARP, Note.E}), "A"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.A_SHARP, Note.D, Note.E_SHARP}), "A Sharp"));
		
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.B_FLAT, Note.D, Note.F}), "B Flat"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.B, Note.D_SHARP, Note.F_SHARP}), "B"));
		
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.C, Note.E, Note.G}), "C"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.C_SHARP, Note.E_SHARP, Note.G_SHARP}), "C Sharp"));
		
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.D_FLAT, Note.F, Note.A_FLAT}), "D Flat"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.D, Note.F_SHARP, Note.A}), "D"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.D_SHARP, Note.G, Note.A_SHARP}), "D Sharp"));
		
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.E_FLAT, Note.G, Note.B_FLAT}), "E Flat"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.E, Note.G_SHARP, Note.B}), "E"));
		
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.F, Note.A, Note.C}), "F"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.F_SHARP, Note.A_SHARP, Note.C_SHARP}), "F Sharp"));
		
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.G_FLAT, Note.B_FLAT, Note.D_FLAT}), "G Flat"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.G, Note.B, Note.D}), "G"));
		CHORDS.add(new Chord(new NoteArray(new Note[] {Note.G_SHARP, Note.B_SHARP, Note.D_SHARP}), "G Sharp"));
	}
	
	private class FileListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == exit){
				System.exit(0);
			}
			
			if(e.getSource() == version){
				JOptionPane.showMessageDialog(null, "Version:\nb.0.0.1");
			}
			
			if(e.getSource() == changeFingering){
				
				for(int str = 0; str < stringNum; str++){
					for(int fret = 0; fret < fretNum - 1; fret++){
						if(fingerings[currentFingering]  == null){
							currentFingering = 0;
						}
						if( fingerings[currentFingering].getPos(str, fret) ){
							strings[str][fret].setText("_--_|");
						}else{
							strings[str][fret].setText("____|");
						}
					}
				}
				currentFingering++;
			}
			
			if(e.getSource() == chordList){ 
				
				gModel.setChord( CHORDS.get( chordList.getSelectedIndex() ) );
				fingerings = gModel.getFingerings();
				
				for(int str = 0; str < stringNum; str++){
					for(int fret = 0; fret < fretNum - 1; fret++){
						if( fingerings[0].getPos(str, fret) ){
							strings[str][fret].setText("_--_|");
						}else{
							strings[str][fret].setText("____|");
						}
					}
				}
				
				currentFingering = 0;

			}
			
		}
		
	}
}
