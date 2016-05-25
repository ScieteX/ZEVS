package zevs;

import java.awt.Color;
import java.util.regex.Matcher;

import javax.swing.text.DefaultHighlighter;

public class CheckData {
	public boolean checkInputText(String text, int type)
	{
		String pattern = null;
		java.util.regex.Pattern p = null;
		switch (type) {
		//only numbers
		case 0:
			pattern = "^[\\d]+$";
			p = java.util.regex.Pattern.compile(pattern);
			break;
			//only English text and numbers (password, login; minimum 3, max 30 characters)
		case 1: 
			pattern = "^[\\w]{3,30}$";
			p = java.util.regex.Pattern.compile(pattern);
			break;	
			//only Russian text
		case 2:pattern = "^[à-ÿÀ-ÿ]+$";
		p = java.util.regex.Pattern.compile(pattern); 
			break;
		}
		Matcher matcher = p.matcher(text);
		
		return matcher.find();
	}
	public boolean checkAllInputText(String text1, String text2, String text3, String text4, String text5, String text6, int type)
	{
		boolean result = false;
		switch(type)
		{
		//idUser available
		case 0: 
			if(text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty() || text5.isEmpty() || text6.isEmpty())
			{
				result = false;
			}
			else {
				result = true;
				}
			break;
		//idUser not available
		case 1: 
			if(text2.isEmpty() || text3.isEmpty() || text4.isEmpty() || text5.isEmpty() || text6.isEmpty())
			{
				result = false;
			}
			else {
				result = true;
				}
			break;
			case 2:
				if(text3.isEmpty() || text4.isEmpty() || text5.isEmpty() || text6.isEmpty())
				{
					result = false;
				}
				else {
					result = true;
					}
			break;
		}
		
		return result;
		
	}
	protected String checkApostrophe(String text)
	{
		return text.replaceAll("'", "''");
	}
    }
