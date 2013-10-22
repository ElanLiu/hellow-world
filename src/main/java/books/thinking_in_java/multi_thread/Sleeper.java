package books.thinking_in_java.multi_thread;

public class Sleeper extends Thread{
	
	private int duration;
	
	public Sleeper(String name, int duration){
		super(name);
		this.duration = duration;
		start();
	}
	
	public void run(){
		try {
			this.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(this.getName() + "has be interrupted!");
		}
		System.out.println(this.getName() + "has awaked!");
	}

}
