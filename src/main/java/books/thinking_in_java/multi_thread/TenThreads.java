package books.thinking_in_java.multi_thread;

import java.util.Timer;
import java.util.TimerTask;


public class TenThreads {
	private static class WorkerThread extends Thread {
        int max = Integer.MIN_VALUE;
        int[] ourArray;

        public WorkerThread(int[] ourArray) {
            this.ourArray = ourArray;
        }

        // Find the maximum value in our particular piece of the array
        public void run() {
            for (int i = 0; i < ourArray.length; i++) 
                max = Math.max(max, ourArray[i]);                
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new CalculatePrimes[10];
        
        // Give each thread a slice of the matrix to work with
        for (int i=0; i < 10; i++) {
            threads[i] = new CalculatePrimes();
            threads[i].start();
        }

        Timer timer = new Timer(true);
        timer.schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.print("Gggggggggg");	
				CalculatePrimes.setFinished(false);
			}
        }, 100);
        
    }
}
