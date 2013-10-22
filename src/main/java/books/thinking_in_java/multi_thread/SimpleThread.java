package books.thinking_in_java.multi_thread;

public class SimpleThread extends Thread{
	
	private int countDown = 5;
	
	private static  int countThread = 0;
	
	public SimpleThread(){
		super("" + ++countThread);
		//setPriority(countThread);
		setDaemon(true);
		
		start();
	}
	
	public String toString(){
		return "#" + this.getName() + ": " + countDown;
	}
	
	public void run(){
		while(true){
			
			//yield();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this);
			if(--countDown == 0)
				return;
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*for(int i=0; i<5;i++){
			try {
				new SimpleThread().join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		//sleep和join练习
		/*Sleeper sl1 = new Sleeper("sl1", 10000);
		Sleeper sl2 = new Sleeper("sl2", 10000);
		Joiner joiner1 = new Joiner("joiner1", sl1);
		
		sl2.interrupt();
		
		Joiner joiner2 = new Joiner("joiner2", sl2);*/
		

		//runnable练习
		for(int i=0; i<5;i++){
			new Thread(new RunnableThread(), "" + i).start();
		}
		
		Anonymous an = new Anonymous("ddd");
		
		
	}

}
