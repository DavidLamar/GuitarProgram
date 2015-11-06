package GuitarProgram;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
	
	static JPanel programPanel;
	static JMenuBar fileBar;
	
	GuitarModel gModel;
	
	private JMenu file, edit, about;
	private JMenuItem exit;
	private JMenuItem preferences;
	private JMenuItem version;
	
	
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
		programPanel = new JPanel();
		
		fileBar = new JMenuBar();
		
		//******************TESTING STUFF
		gModel = new GuitarModel(20, GuitarModel.STANDARD, 6);
		gModel.setChord(Chord.G);
		
		//Testing to make sure notes are right
//		for(int i = 0; i < 6; i++){
//			for(int j = 0; j < 20; j++){
//				System.out.print(gModel.getNoteAt(i, j) + " ");
//			}
//			System.out.println();
//		}
		
		//Fret board stuff:
		boolean[][] frets = gModel.getFretBoard();
		for(int i = 0; i < frets.length; i++){
			for(int j = 0; j < frets[i].length; j++){
				if(frets[i][j] == true){
					System.out.print("__0__");
				}else{
					System.out.print("_____");
				}
				System.out.print("|");
			}
			System.out.println();
		}
		
		
		//*******************END TESTIN STUFF
		
		
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
		
		preferences = new JMenuItem("Preferences");
		preferences.addActionListener(listener);
		edit.add(preferences);
		
		//About stuff
		
		about = new JMenu("About");
		fileBar.add(about);
		
		version = new JMenuItem("Version");
		version.addActionListener(listener);
		about.add(version);
		
	}
	
	private class FileListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == exit){
				System.exit(0);
			}
			
			if(e.getSource() == version){
				JOptionPane.showMessageDialog(null, "Version:\na.0.0.0");
			}
			
		}
		
	}
}
