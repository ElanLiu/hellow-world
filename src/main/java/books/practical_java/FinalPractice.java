package books.practical_java;

public class FinalPractice {
	
	//表示常量
	private static final int i=10;
	
	//表示不能被重写，不能被override
	public final void test1(){}
	
	//这个类不能被集成，没有子类
	public final class inner{
		//final的类中的方法，默认也是final
		public void test2(){}
	}

}
