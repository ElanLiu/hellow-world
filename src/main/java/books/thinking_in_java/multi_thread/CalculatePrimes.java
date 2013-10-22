package books.thinking_in_java.multi_thread;

import org.apache.log4j.Logger;

public class CalculatePrimes extends Thread{
	
	private static final Logger logger = Logger.getLogger(CalculatePrimes.class);

	private static final int MAX_PRIME = 100000;
	
	private static final int TEN_SECONDS = 10000;
	
	private static volatile boolean finished = false;
	
	public static boolean isFinished() {
		return finished;
	}

	public static void setFinished(boolean finished) {
		CalculatePrimes.finished = finished;
	}

	public void run(){
		
		int[] primes = new int[MAX_PRIME];
		int count = 0;
		
		for(int i=2; count<MAX_PRIME; i++){
			if(finished)
				break;
			
			boolean prime = true;
			
			for(int j=0; j<count; j++){
				if(i % primes[j] == 0){
					prime = false;
					break;
				}	
			}
			
			if(prime){
				primes[count++] = i;
				logger.info(Thread.currentThread().getName() + "Found prime: " + i);
				
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CalculatePrimes calculator = new CalculatePrimes();
		calculator.start();
		try {
			Thread.sleep(TEN_SECONDS);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		calculator.finished = true;

	}

}
