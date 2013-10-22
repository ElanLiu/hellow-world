package books.practical_java;

/*
 * 大多情况下，要用到多线程的主要是需要处理大量的IO操作时或处理的情况需要花大量的时间等等，比如：读写文件、视频图像的采集、处理、显示、保存等。
 * 
 * 以卖票为例，描述多线程
 * 
 * 
 */
public class SynchromizedDemo {
	
	
	
	//这是票务中心，其支持多线程，也就是说，他可以同时买多张票
	//显然，这是非线程安全的
	public static class TicketCenter implements Runnable{
		
		//总票数，非线程安全
		private int ticket = 5;
		
		private Integer in = new Integer(5);


		//显然，这样会出现多个窗口同时使用ticket资源，导致：
		/*
		0号票务窗口卖出1张，剩余：3
		1号票务窗口卖出1张，剩余：3
		4号票务窗口卖出1张，剩余：2
		5号票务窗口卖出1张，剩余：1
		2号票务窗口卖出1张，剩余：0
		*/
		public void sell(){
			if(ticket <= 0){
				System.out.println(Thread.currentThread().getName() + "号窗口说：别抢了！！！");
				return;
				}
			ticket--;
			System.out.println( Thread.currentThread().getName() + "号票务窗口卖出1张，剩余：" + ticket);
		}
		
		//对上述方法进行修改，使之符合我们的要求，使用synchronized
		//但是因为是非线性安全，ticket可能在sell函数执行期间被篡改，还需要修改
		public void sell2(){
			synchronized(this){
				if(ticket <= 0){
					System.out.println(Thread.currentThread().getName() + "号窗口说：别抢了！！！");
					return;
					}
				ticket--;
				System.out.println( this.toString() + Thread.currentThread().getName() + "号票务窗口卖出1张，剩余：" + ticket);
			}
		}
		
		//将ticket锁定.
		//synchronized只会锁定当前对象，但是对象内的field或者functioin并不会锁定，需要单独锁定
				public void sell3(){
					synchronized(in){
						synchronized(this){
							if(ticket <= 0){
								System.out.println(Thread.currentThread().getName() + "号窗口说：别抢了！！！");
								return;
								}
							ticket--;
							System.out.println( this.toString() + Thread.currentThread().getName() + "号票务窗口卖出1张，剩余：" + ticket);
						}
					}
				}
		
		//构造一个方法，用来描述售票前卖家和买家的沟通行为，blahblah。。。
		public void blah(){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			blah();
			//sell();
			sell2();
		}
		
	}
	
	public static class inner{
		public static void main(String[] args){
			
			TicketCenter tc1 = new TicketCenter();
			TicketCenter tc2 = new TicketCenter();
			for(int i=0; i<10; i++){
				new Thread(tc1, i + "").start();
				//new Thread(tc2, i + "").start();
			}
			
			
		}
	}

}
