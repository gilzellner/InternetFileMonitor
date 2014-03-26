package Homework.Trusteer.Utils;

import java.util.ArrayList;
import java.util.List;

public class String2List {
	
	public List<String> getListFromString(String content, String Delimiter)
	{
		String[] temp = content.split(Delimiter);
		List<String> templist = new ArrayList<String>();
		for (int i=0; i<temp.length; i++)
		{
			templist.add(temp[i]);
		}
		return templist;
	}

}
