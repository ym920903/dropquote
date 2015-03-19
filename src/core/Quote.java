package core;

public class Quote {

	private String quote;
	private String author;
	private String category;
	private int level;
	private int length;
	

	public Quote(String q, String a, String c, int lev) {
		quote = q;
		author = a;
		category = c;
		level = lev;
		length = q.length();
	}
	
	public Quote(String q, String a, String c) {
		quote = q;
		author = a;
		category = c;
		level = 0;
		length = q.length();
	}
	
	
	public String getQuote(){
		return quote;
	}
	
	public String getAuthor(){
		return author;
		
	}

	public String getCategory(){
		return category;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setQuote(String q){
		quote = q;
		length = quote.length();
	}
	
	public void setAuthor(String a){
		author = a;
	}
	
	public void setCategory(String c){
		category = c;
	}
	
	
	public void setLevel(int l){
		level = l;
	}
}
