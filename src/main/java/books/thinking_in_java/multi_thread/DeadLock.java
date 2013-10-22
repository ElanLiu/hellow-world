package books.thinking_in_java.multi_thread;

public class DeadLock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Object resource1 = "resource1";
		final Object resource2 = "resource2";
		
		new Thread("A"){
			public void run(){
				synchronized(resource1){
					System.out.println(Thread.currentThread().getName() + ": has locked resource1");
					
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					synchronized(resource2){
						System.out.println(Thread.currentThread().getName() + ": has locked resource2");
					}
				}
			}
		}.start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized(resource2){
					System.out.println(Thread.currentThread().getName() + ": has locked resource2");
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					synchronized(resource1){
						System.out.println(Thread.currentThread().getName() + ": has locked resource1");
					}
				}
				
			}}, "B").start();
		
		

	}

}
