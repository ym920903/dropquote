package core;





// The complete implementation along with the documentation 

// will count towards two software engineering assignments
// Software Engineering Assignment 2
// Software Engineering Assignment 3

//Jonathan Wilson
//se assignemnt 2
//ICS 499

/*
 * 
 * The following application is almost complete.  I only created tests for just over half of the methods, and will 
 * finish the testing on the next submission.
 * 
 * 
 * 
 */

import java.util.ArrayList;

/**
 * This class provides several operations in the context of single word.
 * This class is expected to work against multiple languages so the implementation
 * should not rely on the assumption that the string contain characters.
 */


public class WordProcessor {
	
	//word represents the string we are processing in this class
	
	private String word ="";
	
	// logicalChars are derived from the word
	// word can also be derived from logicalChars
	// these two are dependent on each other
	// if one changes, the other changes
	ArrayList<String> logicalChars;
	
	private final int columns = Config.NO_OF_COLUMNS;
	private int rows;
	
	/**
	 * Default constructor
	 */
	public WordProcessor()
	{
		
	}
	
	

	/**
	 * Overloaded constructor that takes the word
	 * @param a_word
	 */
	
	public WordProcessor(String a_word)
	{
		
		setWord(a_word);
		
	}
	
	/**
	 * Overloaded constructor that takes the logical characters as input
	 * @param some_logical_chars
	 */
	public WordProcessor(ArrayList<String> some_logical_chars)
	{
		setLogicalChars(some_logical_chars);
	}
	
	/**
	 * set method for the word
	 * @param a_word
	 */
	public void setWord(String a_word)
	{
		word = new String(a_word);
		parseToLogicalChars();
		findNumberOfRows();
	}
	
	/**
	 * set method for the logical characters
	 * @param some_logical_chars
	 */
	public void setLogicalChars(ArrayList<String> some_logical_chars)
	{
		logicalChars = some_logical_chars;
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ;  i < logicalChars.size() ; i++){
			builder.append(logicalChars.get(i));
		}
		word = builder.toString();
		findNumberOfRows();
	}
	
	public void findNumberOfRows() {

		
		rows = logicalChars.size()/columns;
		//adds another row if needed
		if(logicalChars.size()%columns !=0){
			rows++;
		}
	}


	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	/**
	 * get method for the word
	 * @return
	 */
	public String getWord()
	{
		return word;
	}
	
	/**
	 * get method for the logical characters
	 * @return
	 */
	public ArrayList<String> getLogicalChars()
	{
		return logicalChars;
	}
	
	/**
	 * the method returns a copy of the main words logicalChars
	 * @return
	 */
	
	public ArrayList<String> copyList(){
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0 ; i < logicalChars.size() ; i++){
			list.add(logicalChars.get(i));
		}
		return list;
	}
	
	/**
	 * 
	 * The method returns a copy of an any ArrayList<>
	 * @param a_list
	 * @return
	 */
	public ArrayList<String> copyList(ArrayList<String> a_list){
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0 ; i < a_list.size() ; i++){
			list.add(a_list.get(i));
		}
		return list;
	}
	/**
	 * Returns the length of the word
	 * length = number of logical characters
	 * @return
	 */
	public int getLength()
	{
		return logicalChars.size();
	}
	
	/**
	 * Returns the number of code points in the word
	 * @return
	 */
	public int getCodePointLength()
	{
		
		return word.codePointCount(0, word.length());
	}
	
	/**
	 * This method breaks the input word into logical characters
	 * For Engligh,
	 * 	  convert the string to char array
	 * 	  and convert each char to a string
	 *    and populate logicalChars
	 */
	public void parseToLogicalChars()
	{
		
		logicalChars = new ArrayList<String>();
		char[] charArray = word.toCharArray();
		for(int i = 0 ; i < charArray.length ; i++){
			
			logicalChars.add(Character.toString(charArray[i]));
		}
		
		
		
	}
	
	/**
	 * 
	 * This method was created to be used to in canMakeWord method
	 * 
	 * @param a_word
	 * @return
	 */
	public ArrayList<String> parseToLogicalChars(String a_word)
	{
		
		ArrayList<String> list = new ArrayList<String>();
		char[] charArray = a_word.toCharArray();
		for(int i = 0 ; i < charArray.length ; i++){
			list.add(Character.toString(charArray[i]));
		}
		
		return list;
		
	}
	
	/**
	 * If the word starts with the logical character, 
	 * this method returns true.
	 * @param start_char
	 * @return
	 */
	public boolean startsWith(String start_char)
	{
	
		return word.startsWith(start_char);
	}
	
	/**
	 * If the word ends with the logical character, 
	 * this method returns true.
	 * @param start_char
	 * @return
	 */
	public boolean endsWith(String end_char)
	{
		return word.endsWith(end_char);
	}
	
	/**
	 * This method checks whether the sub_string or logical character
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsString(String sub_string)
	{
		return containsChar(sub_string);
	}
	
	/**
	 * This method checks whether the sub_string or logical character
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsChar(String sub_string)
	{
		return word.contains(sub_string);	
	}
	
	/**
	 * This method checks whether the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalChars(ArrayList<String> logical_chars)
	{
		boolean contains;
		for(int i = 0 ; i < logical_chars.size() ; i++){
			contains = false;
			for(int j = 0 ; j < logicalChars.size() ; j++){
				if(logicalChars.get(j).equals(logical_chars.get(i))){
					contains = true;
				}
			}
			if(!contains){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * This method checks whether *ALL* the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsAllLogicalChars(ArrayList<String> logical_chars)
	{
		if(logicalChars.size() != logicalChars.size()){
			return false;
		}
		boolean contains;
		Boolean[] alreadyChoosen = new Boolean[logicalChars.size()];
		for (int i = 0 ; i < alreadyChoosen.length ; i++){
			alreadyChoosen[i] = false;
		}
		for(int i = 0 ; i < logical_chars.size() ; i++){
			contains = false;
			for(int j = 0 ; j < logicalChars.size() ; j++){
				if(logicalChars.get(j).equals(logical_chars.get(i)) && !alreadyChoosen[j]){
					contains = true;
					alreadyChoosen[j] = true;
					break;
				}
			}
			if(!contains){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method checks whether *ALL* the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * 
	 * The method will loop through the main word.  When a logical character is matched with the first
	 * element in the list supplied by the parameter, it will begin to check if the next
	 * all of the next element in both lists match. When finished, check if a sequence was found.
	 * If so break outer loop and return, else continue to search.
	 *  
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalCharSequence(ArrayList<String> logical_chars)
	{
		//assume there is no sequence
		boolean sequence = false;
		//no point in trying if false
		if(!containsLogicalChars(logical_chars)){
			return false;
		}
		//loops through the main word
		for (int i = 0 ; i < logicalChars.size() ; i++){
			sequence = false;
			//starts the second loop when a character is matched
			if(logical_chars.get(0).equals(logicalChars.get(i))){
				//set found a possible sequence
				sequence = true;
				//loop through the rest of the parameter word
				for (int j = 1 ; j < logical_chars.size() ; j++){
					//if the end of the main word is reached prior to the end of the parameter word, there is no sequence
					if(j+i >= logicalChars.size()){
						sequence = false;
						break;
					//if chars do not match, there is no sequence
					}else if(!logical_chars.get(j).equals(logicalChars.get(j+i))){
						sequence = false;
						break;
					}
				}
				//if a sequence has been found, do not search any further
				if(sequence){
					break;
				}
			}
		}
		return sequence;
	
	
		
		// word = a1b2c3
		// logical_chars = b2c3 ==> true
		// logical_chars = c3b2 ==> false
		
	};
	
	/**
	 * This method checks whether a word can be made out of the original word
	 * example:  original word = POST;   a_word = POT
	 * @param a_word
	 * @return
	 */
	public boolean canMakeWord(String a_word)
	{
		
		ArrayList<String> list = parseToLogicalChars(a_word);
		boolean contains;
		for (int i = 0; i < list.size(); i++){
			contains = false;
			
			for (int j = 0; j < logicalChars.size(); j++){
				if(list.get(i).equals(logicalChars.get(j))){
					contains = true;
					break;
				}
			}
			if(!contains){
				return false;	
			}
		}
		return true;
	}
	
	/**
	 * This method checks whether all the words in the collection
	 * can be made out of the original word
	 * example:  original word = POST;   a_word = POT; STOP; TOP; SOP
	 * @param a_word
	 * @return
	 */
	public boolean canMakeAllWords(ArrayList<String> some_words)
	{
		for (int x = 0 ; x < some_words.size() ; x++){
			if(!canMakeWord(some_words.get(x))){
				return false;
			}			
		}
		return true;
	}
	
	/**
	 * returns true if the word contains the space
	 * @return
	 */
	public boolean containsSpace()
	{
		for (int i = 0 ; i < logicalChars.size() ; i++){
			if(logicalChars.get(i).equals(" ")){
				return true;
			}
		}
		return false;
	};
	
	/**
	 * 
	 * @return
	 */
	public boolean isPalindrome()
	{
		for (int i = 0 ; i < logicalChars.size() ; i++){
			if(!logicalChars.get(i).equals(logicalChars.get(logicalChars.size()-1-i))){
				return false;
			}
		}
		return true;
	
		
		// find the logical characters of the word: we already have those
		// reverse the array list of those logical characters
		// in a loop, keep comparing 1 to N; 1+1, N-2 and so on
		// 
	}
	
	// String manipulation methods
	/**
	 * strips of leading and trailing spaces
	 * @return
	 */
	public String trim()
	{
		String string = "";
		for(int i = 0 ; i < logicalChars.size(); i++){
			string = string + logicalChars.get(i);
		}
		return string.trim();
	}
	
	/**
	 * strips of all spaces in the word
	 * @return
	 */
	public String stripSpaces()
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0 ; i < word.length(); i++){
			if (word.charAt(i) != ' '){
				builder.append(word.charAt(i));
			}
		}
		return builder.toString();
	}
	
	
	
	public ArrayList<String> stripSpaces(ArrayList<String> list)
	{
		ArrayList<String> newList = new ArrayList<String>();
		for(int i = 0 ; i < list.size(); i++){
			if (logicalChars.get(i) != " "){
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	
	/**
	 * strips of all special characters and symbols from the word
	 * @return
	 */
	public String stripAllSymbols(){
		
			StringBuilder builder = new StringBuilder();
			for(int i = 0 ; i < word.length(); i++){
				if(Character.getType(word.charAt(i)) < 10){	
					builder.append(word.charAt(i));
				}
			}
			return builder.toString();
		}
	
	/**
	 * Reverse the word and returns a new word
	 * @return
	 */
	public String reverse()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = logicalChars.size()-1 ; i >= 0 ; i--){
				builder.append(logicalChars.get(i));
		}
		return builder.toString();
	}; 
	
	/**
	 * Replaces a specific sub-string with a substitute_string
	 * if the sub-string is not found, it does nothing
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String replace(String sub_string, String substitute_string)
	{
		String string = "";
		string = string + word;
		return string.replace(sub_string, substitute_string);
		
	}
	
	/**
	 * Add a logical character at the specified index
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String addCharacterAt(int index, String a_logical_char)
	{
		if(index >= logicalChars.size()){
			return word + a_logical_char;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0 ; i < logicalChars.size() ; i++){
			if (i == index){
				builder.append(a_logical_char);
			}
			builder.append(logicalChars.get(i));
			
			
		}
		return builder.toString();
	}
	
	/**
	 * Add a logical character at the end of the word
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String addCharacterAtEnd(String a_logical_char)
	{
		return word + a_logical_char;
	}

	/**
	 * This method gets a logical character at the specified index
	 * @param index
	 * @return
	 */
	public String logicalCharAt(int index)
	{
		return logicalChars.get(index);
	}
	
	/**
	 * This method gets a unicode code point at the specified index
	 * @param index
	 * @return
	 */ 
	public int	codePointAt(int index)

	{
		return word.codePointAt(index);
	}
	
	// Returns the position at which the first logical character is appearing in the string
	
	/**
	 * This method returns the index at which the logical character is appearing
	 * It returns the first appearance of the logical character
	 * returns negative 1 if not present.
	 * @param index
	 * @return
	 */ 
	int indexOf(String logical_char)
	{
		for (int j = 0 ; j < logicalChars.size() ; j++){
			if(logical_char == logicalChars.get(j)){
				return j;
			}
		}
		return -1;
	}
	
	/**
	 * This method compares two strings lexicographically.
	 * It is simplay a wrapper on java compareTo
	 * @param word_2
	 * @return
	 */
	public int	compareTo(String word_2)
	{
		//false if they are not the same length;
		if(word.length() != word_2.length()){
			return word.length() - word_2.length();
		}
		
		for (int i = 0 ; i < word.length(); i ++){
			for (int j = 0 ; j < word_2.length(); j ++){
				//return -1 if word_2 is bigger than word
				if(word.charAt(i) < word_2.charAt(j)){
					return -1;
				//return 1 if word is bigger than word_2
				}else if(word.charAt(i) > word_2.charAt(j)){
					return 1;
				}
			}
		}
		return 0;
	}
	
	
	
	/**
	 * This method compares two strings lexicographically, ignoring case differences.
	 * It is simply a wrapper on java compareTo
	 * @param word_2
	 * @return
	 */
	public int	compareToIgnoreCase(String word_2)
	{
		//false if they are not the same length;
				if(word.length() != word_2.length()){
					return word.length() - word_2.length();
				}
				
				for (int i = 0 ; i < word.length(); i ++){
					for (int j = 0 ; j < word_2.length(); j ++){
						//return -1 if word_2 is bigger than word
						if(word.toLowerCase().charAt(i) < word_2.toLowerCase().charAt(j)){
							return -1;
						//return 1 if word is bigger than word_2
						}else if(word.toLowerCase().charAt(i) > word_2.toLowerCase().charAt(j)){
							return 1;
						}
					}
				}
				return 0;
		}
	
	
	/**
	 * This method takes one collection and returns another randomized collection
	 * of string (or logical characters)
	 * @param some_strings
	 * @return
	 */
	public ArrayList<String> randomize(ArrayList<String> some_strings)
	{
		int random;
		ArrayList<String> list = new ArrayList<String>();
		//copies the array list
		for(int i = 0 ; i < some_strings.size() ; i++){
			list.add(some_strings.get(i));
		}
		//removes a random element and places it in the front of the array list 1000 times
		for (int i = 0 ; i < 1000 ; i ++){
			random = (int)(Math.random()*some_strings.size());
			list.add(list.remove(random));
		}
		return list;
	}
	
	/**
	 * This method splits the word into a 2-dimensional matrix
	 * based on the number of columns
	 * @param no_of_columns
	 * @return
	 */
	public String[][] splitWord(int no_of_columns)
	{
	
		//figure out the number of rows
		int no_of_rows = logicalChars.size()/no_of_columns;
		//adds another row if needed
		if(logicalChars.size()%no_of_columns !=0){
			no_of_rows++;
		}
		String [][] splitWord = new String[no_of_rows][no_of_columns];
		
		for (int i = 0 ; i < no_of_rows * no_of_columns ; i++){
			if(i < logicalChars.size()){
				splitWord[i/no_of_columns][i%no_of_columns] = logicalChars.get(i);	
			}else{
				splitWord[i/no_of_columns][i%no_of_columns] = " ";	
			}
		}
		
		
		return splitWord;
	}
	
	/**
	 * Returns the string representation of WordProcessor
	 * Basially, prints the word and logicalChars
	 */
	public String toString()
	{
		return word;
	}
	
	/**
	 * compares two strings; wrapper on the java method
	 */
	public boolean equals(String word_2)
	{
		if(word.equals(word_2)){
			return true;
		}
		return false;
	}
	
	public String[][] copySplitWord(String[][] temp){
		String[][] stringArray = new String[temp.length][Config.NO_OF_COLUMNS];
		for (int i = 0 ; i < Config.NO_OF_COLUMNS ; i++){
			for (int j = 0 ; j < temp.length ; j++){
				stringArray[j][i] = temp[j][i];
			}
		}
		return stringArray;
	}
	
	/**
	 * compares two strings after reversing the original word
	 */
	public boolean reverseEquals(String word_2)
	{
		
		String string = reverse();
		if(string.equals(word_2)){
			return true;
		}
		return false;
	}
	
	public String[][] randomizeColumns(String [][] string){
		for (int i = 0 ; i < Config.NO_OF_COLUMNS ; i++){
				for (int j = 0 ; j < 1000 ; j++){
					int random = (int)(Math.random()*string.length);
					String temp = string[0][i];
					string[0][i] = string[random][i];
					string[random][i] = temp;
				}
		}
		return string;
	}
	
	public String[][] sortSpaces(String[][] string){
		
		boolean swap;
		for (int i = 0 ; i < Config.NO_OF_COLUMNS ; i++){
			do{
				swap = false;
				for (int j = 0 ; j < string.length-1 ; j++){

					if(!string[j][i].equals(" ")  && string[j+1][i].equals(" ")){
						string[j+1][i] = string[j][i];
						string[j][i] = " ";
						swap = true;
					}
				}
			}while(swap);
	
		}
		return string;
	}
	
	
}
