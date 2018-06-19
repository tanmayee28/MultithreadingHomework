package Producer_Consumer_Problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Producer__Consumer_UsingQueue {

	public static void main(String[] args) {
	Queue q= new LinkedList();
	Producer1 p=new Producer1(q,10,"Producer1");
	Producer1 p1=new Producer1(q,10,"Producer2");
	Consumer1 c=new Consumer1(q,10,"Consumer1");
	Consumer1 c1=new Consumer1(q,10,"Consumer2");
	p.start();
	p1.start();
	c.start();
	c1.start();

	}

}
class Producer1 extends Thread
{
	Queue q=null;
	int max;
	String str;
	public Producer1(Queue q, int max, String str) {
		super(str);
		this.q = q;
		this.max = max;
	}
	public void run()
	{
		while(true)
		{
			synchronized(q)
			{
				System.out.println(Thread.currentThread());
				if(q.size()==max)
				{
					
					System.out.println("Queue is full ..Waiting for consumer to consume it..");
					try {
						q.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				Random random=new Random();
				int no=random.nextInt();
				System.out.println("Produced Element:"+no);
				q.add(no);
				q.notifyAll();
			}
		}
		
	}
	
	
}
class Consumer1 extends Thread
{
	Queue q=null;
	int maxsize;
	String msg;
	public Consumer1(Queue q, int maxsize, String msg) {
		super(msg);
		this.q = q;
		this.maxsize = maxsize;
		//this.msg = msg;
	}
	public void run()
	{
		int i=0;
		while(i<maxsize)
		{
			synchronized(q)
			{
				System.out.println(Thread.currentThread());
				if(q.isEmpty())
				{
				
					System.out.println("No element to consume in queue..Wating for Producer to produce..");
					try {
						q.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Consumed Element:"+q.remove());
				q.notifyAll();
			}
			i++;
		}
	}
	
}