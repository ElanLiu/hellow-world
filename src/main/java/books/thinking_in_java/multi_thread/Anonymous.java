package books.thinking_in_java.multi_thread;

public class Anonymous {
	Thread t;
	public Anonymous(String name){
		t = new Thread(name){
			public void run(){
				System.out.println(Thread.currentThread().getName() + "");
			}
		};
		t.start();
		
	}
	
	public Anonymous(){
		t = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "");
				
			}
			
		});
		t.start();
	}

}
