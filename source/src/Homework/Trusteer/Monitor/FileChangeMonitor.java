package Homework.Trusteer.Monitor;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Homework.Trusteer.Utils.Inventory;
import Homework.Trusteer.Utils.SendEmailViaCommandLine;
import Homework.Trusteer.Utils.Sha1;
import Homework.Trusteer.Utils.String2List;
import Homework.Trusteer.Utils.fileRead;
import Homework.Trusteer.Utils.getFileFromNet;

import org.apache.log4j.BasicConfigurator;



public class FileChangeMonitor {
	
	   static Logger log = Logger.getLogger(FileChangeMonitor.class);
	
	   public static Properties parsePropertiesString(String s) throws IOException {
		    final Properties p = new Properties();
		    p.load(new StringReader(s));
		    return p;
		}
	 public static void main(String args[]) throws IOException  	{

		 BasicConfigurator.configure();

		 log.setLevel(Level.INFO);
		 
		 String configFile = "./config.properties";
		 String fileWithNamesToMonitor = "./fileWithNamesToMonitor.txt";
		 String tempdir = ".";
		 String RunFile = "./mail.sh";
		 String configLog4J = "./log4j.properties";
//		  fileWithNamesToMonitor = "/Users/gil/Dropbox/TrusteerHomeWork/fileWithNamesToMonitor.txt";
//		  tempdir = "/Users/gil/Downloads";
//		  configFile = "/Users/gil/Dropbox/TrusteerHomeWork/config.properties";
//		  configLog4J = "/Users/gil/Dropbox/TrusteerHomeWork/log4j.properties";
//		  RunFile = "/Users/Gil/Dropbox/TrusteerHomeWork/mail.sh";
		 
		 fileRead f = new fileRead();
		 Properties prop = parsePropertiesString(f.readFile(configFile));
		 
					 
		 String SendEmailTo=prop.getProperty("SendEmailTo");
	     PropertyConfigurator.configure(configLog4J);
		 
		 
		 String tempFileName = tempdir + "/tempfile";
		 Inventory i = new Inventory();
		 String2List sl = new String2List();
		 log.trace(f.readFile(fileWithNamesToMonitor));
		 List<String> config = (sl.getListFromString(f.readFile(fileWithNamesToMonitor), "\n|\r"));

		 while (true)
		 {
			 for (String s : config)
			 {
				 String[] temp = s.split(" ");
				 
				 if (!temp[1].equals("*"))
				 {
					 URL u = new URL(temp[0]);
					 log.debug("Replacing hostname: " + u.getHost() + " with " + temp[1]);
					 temp[0]=temp[0].replace(u.getHost(), temp[1]);
					 log.debug("New final URL is: " + temp[0]);

				 }
				 log.trace("Attempting to Download file: " + temp[0] + " to " + tempFileName);
				 try {
					 getFileFromNet g = new getFileFromNet();
					g.saveUrl(tempFileName, temp[0]);
					log.trace("Successfully Downloaded file: " + temp[0]+ " to " + tempFileName);
					Sha1 digest = new Sha1();
					String currentHash = digest.encrypt(f.readFile(tempFileName));
					log.debug("Sha1 Hash for " + temp[0] + " is " + currentHash);
					if (i.hasChanged(temp[0], currentHash))
					{
						log.info("Sha1 Hash for " + temp[0] + " has changed to " + currentHash);

						SendEmailViaCommandLine S = new SendEmailViaCommandLine();
						S.sendEmailLocalHost(SendEmailTo,  
								"\"Sha1 Hash for " + temp[0] + " has changed\"", 
								RunFile);
					}
				} catch (MalformedURLException e) {
					log.error("Unable to download file: " + temp[0]);
				} catch (IOException e) {
					log.error("Unable to download file: " + temp[0]);
				}

				 
			 }
			 log.info("waiting for "+Integer.parseInt(prop.getProperty("CheckInterval", "600"))+" seconds");
			 Homework.Trusteer.Utils.wait w = new Homework.Trusteer.Utils.wait();
			 w.wait_seconds(Integer.parseInt(prop.getProperty("CheckInterval", "600")));
		 }
			

		 
	}
}

