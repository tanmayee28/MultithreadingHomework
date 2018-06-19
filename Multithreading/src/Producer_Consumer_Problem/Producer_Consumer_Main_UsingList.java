package Producer_Consumer_Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer_Consumer_Main_UsingList {

	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		Producer p = new Producer(al, 10, "Prducer Thread");
		Consumer c = new Consumer(al, 10, "Consumer Thread");
		p.start();
		c.start();
	}

}

class Producer extends Thread {
	ArrayList al;
	int maxsize;
	String msg;

	public Producer(ArrayList al, int maxsize, String msg) {
		super(msg);
		this.al = al;
		this.maxsize = maxsize;

	}

	public void run() {
		//int tmp = 0;
		while (true) {
			synchronized (al) {
				if (al.size() == maxsize) {
					System.out.println("Buffere is full..Have to wait for Consumer to consumer");
					try {
						al.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Random no = new Random();
				int i = no.nextInt();
				System.out.println("Producing value:" + i);
				al.add(i);
				al.notify();
			}
			//tmp++;
		}
	}

}

class Consumer extends Thread {
	ArrayList al;
	int maxsize;
	String msg;

	public Consumer(ArrayList al, int maxsize, String msg) {
		super(msg);
		this.al = al;
		this.maxsize = maxsize;

	}

	public void run() {
		//int tmp = 0;
		while (true) {
			synchronized (al) {
				if (al.isEmpty()) {
					System.out.println("Buffere is empty.we can not consume it ..Waiting for Producer to produce..");
					try {
						al.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Element consumed:" + al.remove(0));
				
				al.notify();
			}
			// tmp++;
		}
	}

}