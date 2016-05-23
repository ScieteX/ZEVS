package zevs;

import java.util.regex.Matcher;

public class CheckData {
	protected boolean checkInputText(String text, int type)
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
}
