package Homework.Trusteer.Utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SendEmailViaCommandLine {
	
	public void sendEmailLocalHost(String To, String Subject, String SendFile) throws IOException
	{
		 FileWriter file = new FileWriter(SendFile);
		 file.write("date | mail -s "+ Subject+" "+  To);
		 file.close();
		 Process p = Runtime.getRuntime().exec("/Users/Gil/Dropbox/TrusteerHomeWork/mail.sh");
		 BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 String s;
		 while ((s = stdInput.readLine()) != null) {
		         System.out.println(s);
		 }

	}

}
