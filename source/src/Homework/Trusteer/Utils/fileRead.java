package Homework.Trusteer.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;


public class fileRead {
	
	   static Logger log = Logger.getLogger(fileRead.class);
	   
	
	public String readFile(String filename)
	{
//		log.setLevel(Level.ALL);
	   String content = null;
	   File file = new File(filename); 
	   log.trace("attempting to read " + filename);
	   try {
	       FileReader reader = new FileReader(file);
	       char[] chars = new char[(int) file.length()];
	       reader.read(chars);
	       content = new String(chars);
	       reader.close();
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	   log.trace("successfully read " + filename);
	   return content;
	}

}
