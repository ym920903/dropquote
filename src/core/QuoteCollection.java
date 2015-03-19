package core;

import java.util.ArrayList;

import GUI.DropQuoteGUI;

public class QuoteCollection {
private ArrayList<Quote> quoteCollection;


	public QuoteCollection() {
		DropQuoteGUI.getFileInterface().getQuoteCollection();
	}

	public QuoteCollection(ArrayList<Quote> e) {
		quoteCollection = e;
	}

}
