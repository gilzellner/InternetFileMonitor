package Homework.Trusteer.Utils;

public class wait {
	public void wait_seconds (int seconds)
	{
		 try {
		         {
//		            System.out.println(new Date());
		            Thread.sleep(seconds*1000);
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
	}
}
