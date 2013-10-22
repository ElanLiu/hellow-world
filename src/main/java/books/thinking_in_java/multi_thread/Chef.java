package books.thinking_in_java.multi_thread;

public class Chef extends Thread{
	private Restaurant restaurant;
	
	private WaitPerson waitPerson;
	
	public Chef(Restaurant r, WaitPerson w){
		restaurant = r;
		waitPerson = w;
		start();
	}
	
	
	public void run(){
		if(restaurant.order == null){
			restaurant.order = new Order();
			System.out.print("order up!");
			
			synchronized(waitPerson){
				waitPerson.notify();
			}
		}
		
		try{
			sleep(100);
		}catch(InterruptedException e){
			throw new RuntimeException(e);
		}
	}

}
