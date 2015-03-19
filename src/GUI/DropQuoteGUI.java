package GUI;



import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import te.TeluguWordProcessor;
import core.FileInterface;
import core.WordProcessor;


public class DropQuoteGUI {
	JFrame frame;
	private JMenu fileMenu;
	private JMenuItem newGame;
	private JMenuItem quit;
	private JMenu optionsMenu;
	private JMenuBar menuBar;
	private static FileInterface fileInterface = new FileInterface();
	static WordProcessor processor = new WordProcessor();
	
	public DropQuoteGUI() {
		frame = new JFrame();
		frame.setContentPane(new GameBoardPanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		newGame = new JMenuItem("New Game");
		quit = new JMenuItem("Quit");
		frame.setMinimumSize(new Dimension(1118,750));
		fileMenu = new JMenu("File");
		fileMenu.add(newGame);
		fileMenu.add(quit);
		optionsMenu = new JMenu("Options");
		
		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(optionsMenu);
		frame.setJMenuBar(menuBar);
	}
	
	public static void setTelegu(){
		processor = new TeluguWordProcessor();
	}
	
	public static void setEnglish(){
		processor = new WordProcessor();
	}

	public JFrame getFrame(){
		return frame;
	}

	public static FileInterface getFileInterface() {
		return fileInterface;
	}


}
