package books.thinking_in_java.multi_thread;

public class ThreadDemo {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		new Thread("A"){
			public void run(){
				for(int i=0; i<5; i++)
					compute();
			}
		}.start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0; i<5; i++)
					compute();
			}
			
		}, "B").start();
		
		for(int i=0; i<5; i++)
			compute();
	}
		
		

	

	static ThreadLocal tl = new ThreadLocal();
	public  static void compute(){
		Integer in = (Integer) tl.get();
		
		if(in == null)
			in = new Integer(1);
		
		else
			in = new Integer(in.intValue() + 1);
		
		tl.set(in);
		
		System.out.println(Thread.currentThread().getName() + ": " + in);
		
		for(int i=0 ,j=0; i<100000; i++)
			j +=i;
		
		try{
		Thread.sleep((long) (Math.random() * 100 + 1));
		}catch( InterruptedException e){}
		
		Thread.yield();
		
		
	}

}
