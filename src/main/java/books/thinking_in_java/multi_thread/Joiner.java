package books.thinking_in_java.multi_thread;

public class Joiner extends Thread{
	
	private Sleeper sleeper;
	
	public Joiner(String name, Sleeper sleeper){
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	public void run(){
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		System.out.println(this.getName() + "has joined!");
	}

}
