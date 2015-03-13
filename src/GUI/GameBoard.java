package GUI;







/** 


tado
 * @author:			Jonathan Wilson
 * @date_written:	March 3 2015
 * 
 * The following application is create to play a dropquote game.  It is intended to be built into a larger product for educational
 * purposes only.  Any attempt to use this application for reasons other than that stated above is strictly prohibited.
 * 
 * 
 * -------------------------------------------------------------------------------------------------------------------------------
 *      											- START - 
 * -------------------------------------------------------------------------------------------------------------------------------     
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.MouseInfo;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.JTextPane;

import te.TeluguWordProcessor;import core.Config;
import core.FileInterface;
import core.WordProcessor;


public class GameBoard extends JPanel {

	public static final int NO_COLUMNS = Config.NO_OF_COLUMNS;
	public static int NO_ROWS;
	
	//global variables

	ImageIcon buttonPressedIcon;
	ImageIcon buttonOffIcon;
	ImageIcon buttonOnIcon;
	
	
	FileInterface fileInterface = new FileInterface();
	private WordProcessor processor;
	private String string;
	private String [][] sortedArray;
	private String [][] wordArray;
	private final int CLUE_BUTTON = 1;
	private final int SOLUTION_BUTTON = 2;
	
	//Containers
	private JButton shuffleButton = new JButton("shuffle");
	private JButton clearButton = new JButton("Clear");
	private JPanel clue_panel;
	private JPanel title_panel;
	private JPanel gameboard_panel;
	private JPanel solution_panel;
	private JPanel button_panel;
	MyButton[][] solutionButtons;
	MyButton[][] clueButtons;
	
	private JLabel gameTitleLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new DropQuoteGUI();
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	public GameBoard() {
		
		//sets the look and feel
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		   
		}
	  
		buttonPressedIcon = fileInterface.loadImageIcon(Config.BUTTON_PRESSED_IMAGE);
		buttonOnIcon = fileInterface.loadImageIcon(Config.BUTTON_ON_IMAGE);
		buttonOffIcon = fileInterface.loadImageIcon(Config.BUTTON_OFF_IMAGE);
		
		string = "This is a test of the emergency Broadcast System";
		processor = new WordProcessor(string.toUpperCase());
		wordArray = processor.splitWord(NO_COLUMNS);
		sortedArray = processor.randomizeColumns(wordArray);
		NO_ROWS = processor.getRows();
		init();
	}
	/**
	 * Create the interface.
	 */
	public void init(){
		createSolutionPanel();
		createCluePanel();
		populateCluePanel();
		setPreferredSize(new Dimension(1200,750));
		setLayout(new BorderLayout());

		gameboard_panel = new JPanel();
		gameboard_panel.setLayout(new GridLayout(2,0,5,5));
		gameboard_panel.setBorder(new EmptyBorder(500-(NO_ROWS*100),20,20,20));

		gameboard_panel.add(clue_panel);
		gameboard_panel.add(solution_panel);
		title_panel = new JPanel();
		gameTitleLabel = new JLabel("DROPQUOTE");
		gameTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		gameTitleLabel.setFont(gameTitleLabel.getFont().deriveFont(40.0f));
		title_panel.add(gameTitleLabel);
		button_panel = new JPanel();
		shuffleButton.addActionListener(new ShuffleButtonActionListener());
		clearButton.addActionListener(new ClearButtonActionListener());
		clearButton.setFocusable(false);
		shuffleButton.setFocusable(false);
		button_panel.add(shuffleButton);
		button_panel.add(clearButton);
		button_panel.setBorder(new EmptyBorder(0,20,20,20));
		add(title_panel, BorderLayout.NORTH);
		add(gameboard_panel, BorderLayout.CENTER);
		add(button_panel, BorderLayout.SOUTH);
	}
	
	//This method creates the panel to hold the clue letters
	public void createCluePanel(){
		//instantiates a new panel
		clue_panel = new JPanel();
		
		clue_panel.setLayout(new GridLayout(NO_ROWS, NO_COLUMNS, 0, 0));
		//instantiates an array of buttons
		clueButtons = new MyButton[NO_ROWS][NO_COLUMNS];
		//creates each button and places it in the panel
		for(int i = 0; i < NO_ROWS*NO_COLUMNS; i++){

			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS] = new MyButton(i/NO_COLUMNS, i%NO_COLUMNS, CLUE_BUTTON);
			//clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].addActionListener(new ClueButtonActionListener());
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setFont(fileInterface.getFont());
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIcon(buttonOffIcon);
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].addMouseListener(new ButtonMouseListener());
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setHorizontalTextPosition(JButton.CENTER);
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setVerticalTextPosition(JButton.CENTER);
			//sets the borders around buttons for L&F
			if(i+1 == (NO_ROWS * NO_COLUMNS)){
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
			}
			else if(i/NO_COLUMNS == NO_ROWS -1){
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.black));
			}else if((i+1)%NO_COLUMNS == 0){
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.black));
			}else{
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setBorder(BorderFactory.createMatteBorder(1,1,0,0,Color.black));
			}
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setFocusable(false);
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(sortedArray[i/NO_COLUMNS][i%NO_COLUMNS]);
			clue_panel.add(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS]);
		}	
	}
	
	
	//this method creates the panel used to hold the solution letters
	public void createSolutionPanel(){
		//instantiates a panel
		solution_panel = new JPanel();
		solution_panel.setLayout(new GridLayout(NO_ROWS, NO_COLUMNS, 0, 0));
		//instantiates an array of buttons
		solutionButtons = new MyButton[NO_ROWS][NO_COLUMNS];
		//creates each button and adds it to the panel
		for(int i = 0; i < NO_ROWS*NO_COLUMNS; i++){
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS] = new MyButton(i/NO_COLUMNS, i%NO_COLUMNS, SOLUTION_BUTTON);
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setFont(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getFont().deriveFont(20.0f));
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setFocusable(false);
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIsEmpty(true);
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setFont(fileInterface.getFont());
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].addActionListener(new SolutionButtonActionListener());
			if(i < processor.getLogicalChars().size()){
				if(processor.getLogicalChars().get(i).equals(" ")){
					solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(" ");
					solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setEnabled(false);
					solution_panel.add(new EmptyPanel());
				}else{
					
					solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setHorizontalAlignment(JTextField.CENTER);
					solution_panel.add(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS]);
					solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(" ");
				}
			}else{
				solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(" ");
				solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setEnabled(false);
				solution_panel.add(new EmptyPanel());
			}	
		}
	}

	public void populateCluePanel(){
		sortedArray = processor.sortSpaces(sortedArray);
		for(int i = 0; i < NO_ROWS * NO_COLUMNS; i++){
			solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setFocusable(false);
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(sortedArray[i/NO_COLUMNS][i%NO_COLUMNS]);
			clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIcon(buttonOffIcon);
			if(sortedArray[i/NO_COLUMNS][i%NO_COLUMNS].equals(" ")){
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIsEmpty(true);
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setEnabled(false);
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setOpaque(false);
			}else{	
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setEnabled(true);
				clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIsEmpty(false);
			}
		}
	}
	
	//flips the text values in a button
	private void flipValues(MyButton myButton, MyButton myButton2) {
		String tempString = myButton.getText();
		boolean tempBool = myButton.isEmpty();
		myButton.setIsEmpty(myButton2.isEmpty);
		myButton2.setIsEmpty(tempBool);
		myButton.setText(myButton2.getText());
		myButton2.setText(tempString);
	}
	
	//removes a letter from the solution panel and places it on the clue panel
	public void addToCluePanel(MyButton button){
		for (int i = 0 ; i < NO_ROWS ; i++){
			if(clueButtons[i][button.getColumn()].isEmpty()){
				clueButtons[i][button.getColumn()].setIsEmpty(false);
				sortedArray[i][button.getColumn()] = button.getText();
				button.setText(" ");
				button.setIsEmpty(true);
				break;
			}
		}
	}
	public void quickAddToSolutionPanel(MyButton button){
		for (int i = 0 ; i < NO_ROWS ; i++){
			if(solutionButtons[i][button.getColumn()].isEmpty() && solutionButtons[i][button.getColumn()].isEnabled()){
				
				solutionButtons[i][button.getColumn()].setIsEmpty(false);
				solutionButtons[i][button.getColumn()].setText(button.getText());
				button.setClick();
				sortedArray[button.getRow()][button.getColumn()] = " ";
				button.setText(" ");
				button.setIsEmpty(true);
				break;
			}
		}
	}
	
	
	
/** 
 * @author Jonathan
 * -------------------------------------------------------------------------------------------------------------------------------
 *      											- ACTIONLISTENERS - 
 * -------------------------------------------------------------------------------------------------------------------------------     
 */
	
	//Action listener for the buttons in the clue panel
	private class ClueButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// for all buttons
			for (int i = 0 ; i < NO_ROWS*NO_COLUMNS ; i++){
				//if a clue button is already clicked
				if(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					//if they are the same button remove the click
					if(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getRow() == ((MyButton)e.getSource()).getRow() && 
							clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn() == ((MyButton)e.getSource()).getColumn()){
						if(System.currentTimeMillis() - clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getTimeOfLastClick() <= 500){
							quickAddToSolutionPanel(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS]);
							populateCluePanel();
						}else{
							((MyButton)e.getSource()).setClick();	
						}
						return;
					}else{
						//otherwise remove the first click and add the second
						((MyButton)e.getSource()).setClick();
						clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						return;
					}			
					/*
					 * Consider code modification here
					 */
	
				//if a solution button is already clicked
				}else if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					//if its in the same column flip out the buttons
					if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn() == ((MyButton)e.getSource()).getColumn()){
						solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						sortedArray[((MyButton)e.getSource()).getRow()][((MyButton)e.getSource()).getColumn()] = solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getText();
						//solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(((MyButton)e.getSource()).getText());
						//solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIsEmpty(false);
						flipValues(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS],(MyButton)e.getSource());
						return;			
					}else{
						//otherwise remove the first click and add the second
						solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						((MyButton)e.getSource()).setClick();
						return;
					}
				}
			}
			//lastely sets the click if nothig else is clicked
			((MyButton)e.getSource()).setClick();
		}
	}
	
	//action listener for the buttons in the solution panel
	private class SolutionButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//for all buttons
			for (int i = 0 ; i < NO_ROWS*NO_COLUMNS ; i++){
				//check if a clue button is already clicked
				if(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					//if they are in the same column
					if(((MyButton)e.getSource()).getColumn() == clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn()){
						//swap the letters
						clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						sortedArray[i/NO_COLUMNS][i%NO_COLUMNS] = ((MyButton)e.getSource()).getText();									
						flipValues(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS],(MyButton)e.getSource());
						populateCluePanel();
						return;							
					}else{
						//otherwise removes first the click
						clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						//adds the second click if the button has a letter
						if(!((MyButton)e.getSource()).isEmpty()){
							((MyButton)e.getSource()).setClick();
						}
						return;
					}
				//if a solution button is already clicked
				}else if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					//if it is the same button, add the letter back to the clue panel
					if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getRow() == ((MyButton)e.getSource()).getRow() && 
							solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn() == ((MyButton)e.getSource()).getColumn()){
						if(System.currentTimeMillis() - solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getTimeOfLastClick() <= 500){
							((MyButton)e.getSource()).setClick();
							addToCluePanel(((MyButton)e.getSource()));
							populateCluePanel();
						}else{
							((MyButton)e.getSource()).setClick();
						}
						return;
					//otherwise if they are in the same column flip the letter values
					}else if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn() == ((MyButton)e.getSource()).getColumn()){
						flipValues(((MyButton)e.getSource()),solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS]);	
						solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();		
					return;
					}else{
						//otherwise remove first click
						solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						//add a click if a letter is present
						if(!((MyButton)e.getSource()).isEmpty()){
							((MyButton)e.getSource()).setClick();
						}
						return;
					}
				}
			}
			//if nothing else is selected, and the button has a letter, add a click
			if(!((MyButton)e.getSource()).isEmpty()){
				((MyButton)e.getSource()).setClick();
			}
		}
	}
	
	//shuffles the letters in the clue panel
	public class ShuffleButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			sortedArray = processor.randomizeColumns(sortedArray);
			populateCluePanel();
		}
	}
	
	//clears all the letters in the solution panel and places them back into the clue panel
	public class ClearButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			for(int i = 0 ; i < NO_ROWS*NO_COLUMNS ; i++){
				if(!solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].isEmpty()){
					addToCluePanel(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS]);
				}
				if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
				}
				if(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
				}
			}
			populateCluePanel();
		}
	}
	
	private class ButtonMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			((MyButton)e.getSource()).setIcon(buttonPressedIcon);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(!((MyButton)e.getSource()).isEnabled()){
				((MyButton)e.getSource()).setIcon(buttonOffIcon);
				return;
			}
			double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
			double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
			double buttonX = ((MyButton)e.getSource()).getLocationOnScreen().getX();
			double buttonY = ((MyButton)e.getSource()).getLocationOnScreen().getY();
			double width = ((MyButton)e.getSource()).getWidth();
			double height = ((MyButton)e.getSource()).getHeight();
			if(mouseX < buttonX || mouseY < buttonY || mouseX > buttonX + width || mouseY > buttonY+height){
				((MyButton)e.getSource()).setIcon(buttonOffIcon);
				return;
			}
			for (int i = 0 ; i < NO_ROWS*NO_COLUMNS ; i++){
				if(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					//check if a clue button is already clicked
					if(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getRow() == ((MyButton)e.getSource()).getRow() &&
							clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn() == ((MyButton)e.getSource()).getColumn()){
						if(System.currentTimeMillis() - clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].getTimeOfLastClick() <= 500){
							quickAddToSolutionPanel(clueButtons[i/NO_COLUMNS][i%NO_COLUMNS]);
							populateCluePanel();
						}else{
							((MyButton)e.getSource()).setClick();
							((MyButton)e.getSource()).setIcon(buttonOffIcon);
						}
						return;
					}else{
						clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						clueButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIcon(buttonOffIcon);
						((MyButton)e.getSource()).setClick();
						((MyButton)e.getSource()).setIcon(buttonOnIcon);
						return;
					}
				}else if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].isClicked()){
					//if its in the same column flip out the buttons
					if(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getColumn() == ((MyButton)e.getSource()).getColumn()){
						solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						sortedArray[((MyButton)e.getSource()).getRow()][((MyButton)e.getSource()).getColumn()] = solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].getText();
						//solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setText(((MyButton)e.getSource()).getText());
						//solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setIsEmpty(false);
						flipValues(solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS],(MyButton)e.getSource());
						((MyButton)e.getSource()).setIcon(buttonOffIcon);
						return;			
					}else{
						//otherwise remove the first click and add the second
						solutionButtons[i/NO_COLUMNS][i%NO_COLUMNS].setClick();
						((MyButton)e.getSource()).setClick();
						return;
					}
				}
				
			}
			((MyButton)e.getSource()).setIcon(buttonOnIcon);
			((MyButton)e.getSource()).setClick();
			
			// TODO Auto-generated method stub
			
		}
	
	}

	
	/** 
	 * @author Jonathan
	 * -------------------------------------------------------------------------------------------------------------------------------
	 *      											- INNER CLASSES - 
	 * -------------------------------------------------------------------------------------------------------------------------------     
	 */
	
	
	
	//Modified button to hold information about its status and location
	public class MyButton extends JButton{
		private int row;
		private int column;
		private String letter;
		private boolean isClicked;
		private boolean isEmpty;
		private int buttonType;
		private double lastClickTime;
		
		public MyButton(int r, int c, int type){
			buttonType = type;
			row = r;
			column = c;
			letter = "";
			isClicked = false;
			isEmpty = false;
		}
		
		public int getRow(){
			return row;
		}
		
		public int getColumn(){
			return column;
		}
		
		public String getLetter(){
			return letter;
		}
		
		public void setRow(int r){
			row = r;
		}
		
		public void setColumn(int c){
			column = c;
		}
		
		public void setLetter(String l){
			letter = l;
		}
		
		public void setClick(){
			if(isClicked){
				isClicked = false;
				setBackground(null);
			}else{
				lastClickTime = System.currentTimeMillis();
				isClicked = true;
				setBackground(Color.green);
			}
			
		}
		
		public boolean isClicked(){
			return isClicked;
		}
		
		public boolean isEmpty(){
			return isEmpty;
		}
		
		public void setIsEmpty(boolean b){
			isEmpty = b;
		}
		
		public int getType(){
			return buttonType;
		}
		
		public double getTimeOfLastClick(){
			return lastClickTime;
		}
	}
	
	//modified JPanel to be inserted into the solution panel as blank spaces
	private class EmptyPanel extends JPanel{
		
		public EmptyPanel(){
			setBackground(Color.black);
			setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.white));
		}
	}
}
/** 
 * -------------------------------------------------------------------------------------------------------------------------------
 *      											- END - 
 * -------------------------------------------------------------------------------------------------------------------------------     
 */