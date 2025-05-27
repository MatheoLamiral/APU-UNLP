package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.HideHandler;

import java.util.List;
import java.util.logging.*;

public class HideFormatter extends Formatter{
	private List<String> bannedWords;
	
	public HideFormatter(List<String> bannedWords) {
		super();
		this.bannedWords = bannedWords;
	}
	
	@Override
	public String format(LogRecord record) {
		String[] words = record.getMessage().split(" ");
		StringBuilder finalWord = new StringBuilder();
		for(String word : words) {
			if(this.bannedWords.contains(word)) {
				finalWord.append("*** ");
			}
			else {
				finalWord.append(word+" ");				
			}
		}
		return finalWord.toString();
	}
	
	public void addBannedWord(String word) {
		this.bannedWords.add(word);
	}


}
