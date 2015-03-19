package te;
// The complete implementation along with the documentation 
// will count towards two software engineering assignments
// Software Engineering Assignment 2
// Software Engineering Assignment 3


import java.util.*;

import core.WordProcessor;


/**
 * This class provides several operations in the context of single word.
 * This class is expected to work against multiple languages so the implementation
 * should not rely on the assumption that the string contain characters.
 */

public class TeluguWordProcessor extends WordProcessor {
	
	
	// Instantiating the Telugu LetterProcessor
	LetterProcessor te = new LetterProcessor();
	
	/**
	 * Overloaded constructor that takes the word
	 * @param a_word
	 */
	public TeluguWordProcessor(){
	
	}
	public TeluguWordProcessor(String a_word)
	{
		setWord(a_word);
	}
	
	/**
	 * Overloaded constructor that takes the logical characters as input
	 * @param some_logical_chars
	 */
	public TeluguWordProcessor(ArrayList<String> some_logical_chars)
	{
		setLogicalChars(some_logical_chars);
	}
	
	
	
	/**
	 * Telugu method - overriding the parent method in WordProcessor
	 * 
	 * Parses the specified string of characters in a native script into a list
	 * of logical  characters.
	 * 
	 * It populates  "ArrayList<String> logicalChars;" from the "String word"
	 */

	public void parseToLogicalChars() {
		// get the array of logical characters from Telugu Letter Processor
		
	   String[] logical_chars =   te.parseStringToLogicalCharacters(getWord());
	   // then convert the data type to array list
	   
	   List<String> list_of_logical_chars = Arrays.<String>asList(logical_chars);
	   ArrayList<String> array_list_of_logical_chars = new ArrayList<String>(list_of_logical_chars);
	   this.setLogicalChars(array_list_of_logical_chars);  
	   
	}
	
	public ArrayList<String> parseToLogicalChars(String a_word) {
		// get the arry of logical characters from Telugu Letter Processor
		
	   String[] logical_chars =   te.parseStringToLogicalCharacters(a_word);
	   // then convert the data type to array list
	   List<String> list_of_logical_chars = Arrays.<String>asList(logical_chars);
	   ArrayList<String> array_list_of_logical_chars = new ArrayList<String>(list_of_logical_chars);
	    
	   return array_list_of_logical_chars;
	   
	}

	
	
	
	
}

/*
 * An Internal private class Used to compare two Letters based on their
 * Unicode values
 */
 class LetterCompareUnicode implements Comparator<Letter> {

	/*
	 * Compares the Unicode value of the two specified Letters for order.
	 * 
	 * Compares the Unicode value of the two specified Letters for order.
	 * Returns a negative integer, zero, or a positive integer as the
	 * Unicode value of the first Letter is less than, equal to, or greater
	 * than the Unicode value of the second Letter.
	 * 
	 * @param compare_1 The first Letter to be compared.
	 * 
	 * @param compare_2 The second Letter to be compared.
	 * 
	 * @return a negative integer, zero, or a positive integer as the
	 * Unicode value of the first Letter is less than, equal to, or greater
	 * than the Unicode value of the second Letter.
	 */
	@Override
	public int compare(Letter compare_1, Letter compare_2) {
		return (int) (compare_1.getUnicode() - compare_2.getUnicode());
	}
}
