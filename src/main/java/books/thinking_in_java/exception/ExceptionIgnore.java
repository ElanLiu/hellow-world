package books.thinking_in_java.exception;

public class ExceptionIgnore {
	
	public static void exceptionOne() throws Exception{
		System.out.println("exceptionOne");
		throw new Exception();
		
	}
	
	public static void exceptionTwo() throws Exception{
		System.out.println("exceptionTwo");
		throw new Exception();
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		try {
			exceptionOne();
		} finally{
			exceptionTwo();
		}


	}

}
