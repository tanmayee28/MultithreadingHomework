package Producer_Consumer_Problem;

import java.util.ArrayList;
import java.util.Random;

public class Producer_Consumer_Alternate {

	public static void main(String[] args) {
		ArrayList al=new ArrayList();
		Producer2 p=new Producer2(al,"Producer");
		Consumer21 c=new Consumer21(al,"Consumer");
		p.start();
		c.start();

	}

}
class Producer2 extends Thread
{
	ArrayList al=null;
	String msg;
	 boolean flg=false;
	public Producer2(ArrayList al,String msg) {
		super(msg);
		this.al = al;
	}
	 public void run()
	 {
		
		 while(true)
		 {
			 synchronized(al)
			 {
				 try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 if(al.size()==0){
					 int i=new Random().nextInt(50);
					 //int i=no.nextInt();
					 al.add(i);
					 System.out.println("Produced element : "+i);
					 //flg=true;
					 al.notify();
				 }
				 else{
					 try {
						al.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 al.notify();
				 }
			 	}
			
		 	}
		 	
		}
	}
class Consumer21 extends Thread
{
	ArrayList al=null;
	int maxsize;
	String msg;
	boolean flg=false;
	Consumer21(ArrayList al, String msg) {
		super();
		this.al = al;
		this.msg = msg;
	
	}
	public void run()
	{
		int tmp=0;
		while(true)
		{
			synchronized(al)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(al.size()==1){
				System.out.println("Consumed Item:"+al.remove(0));
				al.notify();
				}
				else{
					try {
						al.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					al.notify();
				}
				
			}
			
		}
		}	
	
}