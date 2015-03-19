package core;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;







import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class FileInterface {
final static Logger LOGGER = Logger.getLogger(FileInterface.class
			.getName());
	
	

	public FileInterface() {
		
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public static String loadWords() throws Exception {
		
		String line;
		
		InputStream fis = new FileInputStream("src\\te\\word.txt");
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"The Word File Specified Is Not Encoded As UTF-8.");
			
			throw new Exception("Encoding Error");
		}
		// Loop file while data exists.
		try {
			line = br.readLine();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"There Was An Error Reading Valid Data From The File.");
			
			throw new Exception("File read error");
		}
		return line;
	}
	
	public Font getFont(){
		
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT,  
					getClass().getClassLoader().getResourceAsStream(Config.fontFile)).deriveFont(35.0f);
			return font;
		} catch (FontFormatException | IOException e) {
			LOGGER.warning("ERROR: Failed to load font file " + Config.fontFile + "some character may not be displayed");
			// TODO Auto-generated catch block
			Font font = new Font("Roman Baseline", Font.ROMAN_BASELINE, 20);
			e.printStackTrace();
			return font;
		}
	}
	
	public ImageIcon loadImageIcon(String path){
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(path)));
	}
	
	public void playSound(String fileName, boolean loop) {
		      try {
		        Clip clip = AudioSystem.getClip();
		        File file = new File(fileName);
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
		        clip.open(inputStream);
		        if(loop){
		        	clip.loop(Clip.LOOP_CONTINUOUSLY);
		        }
		        FloatControl gainControl = 
		        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        	gainControl.setValue(-40.0f); // Reduce volume by 10 decibels
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		}
	
	public ArrayList<Quote> getQuoteCollection(){
		ArrayList<Quote> list = new ArrayList<Quote>();
		return list;
		
	}
		
	
}
