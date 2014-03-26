package Homework.Trusteer.Utils;

import java.io.*;
import java.net.MalformedURLException;
import java.security.MessageDigest;

public class MD5Checksum {

   public static byte[] createChecksum(String filename) throws Exception {
       InputStream fis =  new FileInputStream(filename);

       byte[] buffer = new byte[1024];
       MessageDigest complete = MessageDigest.getInstance("MD5");
       int numRead;

       do {
           numRead = fis.read(buffer);
           if (numRead > 0) {
               complete.update(buffer, 0, numRead);
           }
       } while (numRead != -1);

       fis.close();
       return complete.digest();
   }

   // see this How-to for a faster way to convert
   // a byte array to a HEX string
   public static String getMD5Checksum(String filename) throws Exception {
       byte[] b = createChecksum(filename);
       String result = "";

       for (int i=0; i < b.length; i++) {
           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
       }
       return result;
   }

//   public static String getMd5ForFileFromNet (String tempDir, String URL)
//   {
//	   	getFileFromNet g = new getFileFromNet();
//	   	try {
//			g.saveUrl(tempDir+"/tempfile", URL);
//		   	return getMD5Checksum(tempDir+"/tempfile");
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	   	return null;
//   }

   public String getMd5ForFileFromNet (String tempDir, String URL)
   {
	   	getFileFromNet g = new getFileFromNet();
	   	try {
			g.saveUrl(tempDir+"/tempfile", URL);
		   	return getMD5Checksum(tempDir+"/tempfile");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	return null;
   }

   
//   public static void main(String args[]) {
//       try {
//           System.out.println(getMd5ForFileFromNet("/Users/gil/Downloads","https://dl.dropboxusercontent.com/u/28848111/CV-english.pdf"));
//           
//           // output :
//           //  0bb2827c5eacf570b6064e24e0e6653b
//           // ref :
//           //  http://www.apache.org/dist/
//           //          tomcat/tomcat-5/v5.5.17/bin
//           //              /apache-tomcat-5.5.17.exe.MD5
//           //  0bb2827c5eacf570b6064e24e0e6653b *apache-tomcat-5.5.17.exe
//       }
//       catch (Exception e) {
//           e.printStackTrace();
//       }
//   }
}