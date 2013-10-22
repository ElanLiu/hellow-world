package books.thinking_in_java.multi_thread;

import org.apache.log4j.Logger;

public class AlwaysEvent {
	
	private int i;
	
	private static final Logger logger = Logger.getLogger(AlwaysEvent.class);
	
	public synchronized void next(){
		++i;
		++i;
		logger.info(Thread.currentThread().getName());
		
	}
	
	public synchronized int getValue(){
		return i;
	}
	
	public static void main(String[] args){
		final AlwaysEvent ae = new AlwaysEvent();
		
		
		new Thread("watcher"){
			public void run(){
				
				while(true){
					ae.next();
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int value = ae.getValue();
					logger.info("value: " + value);

					if(value % 3 != 0){
						logger.info("value:=== " + value);
						//return;
					}
				}
			}
		}.start();
		
		
		
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					ae.next();
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
		}, "Another").start();
		
		
			
	}

}
