package books.practical_java;

/**
 * 
 * @author yu.liuyly
 * 
 *         一个普遍存在的误解是：java 中的参数以by reference 方式传递。这不是真 的，参数其实是以by value
 *         方式传递。这个误解源于[所有java objects 都是 objects references]事实（关于object
 *         references 的详细信息，请见实践8）。 如果你未能准确理解其中奥妙，则可能导致一些料想不到的后果。
 * 
 */

/*
 * 1 有意思的是，modify方法，传入2个变量，一个是引用型变量，一个是基础变量。2个变量在modify中都被改变了值，但是
 * 在main方法中，point的值被改变了，而基础变量的值没有改变。
 * 
 * 2 实际上，基本变量，传进modify的是副本（clone），所以原来的值没有改变，只是副本改变了；
 * 而引用变量，传进去的是引用的副本，也就是说，该引用和和副本指向了同一个内存地址，所以全都改变了。
 */

/*
 * 3 继续看了例子2，彻底凌乱了，上面说的也不对，
 */

/*
 * 知道看到这篇，才豁然开朗：
 * 
 * 从编译原理的角度讲，所有那些无法通过形参来修改实参本身的传递机制都是“值传递”，
 * 在JAVA中，你只能通过在形参上调用方法来修改实参所引用的那个对象，却修改不了实参本身（不能使它引用到另个对象）
 * 
 * 总结：也就是说，1是对的，列子2，只是改变了副本的引用，而不是改变真是的引用对象，所以变换失败
 */
public class PassByValue {

	public static class Point {
		private int i;
		private int j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public void setLocation(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public String toString() {
			return "i= " + i + ", j= " + j;
		}

	}

	public static void modifyPoint(Point pt, int j) {
		pt.setLocation(5, 5); // 1
		j = 15;
		System.out.println("pt: " + pt + ", j= " + j);
	}

	public static void tricky(Point arg1, Point arg2) {
		arg1.setLocation(100, 100);
		Point temp = arg1;
		arg1 = arg2;
		arg2 = temp;
	}

	public static class inner {
		public static void main(String[] args) {
			PassByValue p = new PassByValue();
			Point pt = new Point(0, 0); // 2
			int i = 10;
			System.out.println("pt: " + pt + ", i= " + i);

			modifyPoint(pt, i); // 3

			System.out.println("pt: " + pt + ", i= " + i);
			//===============下面是例子2，例子1不够明显===================//
			
			Point pt1 = new Point(0,0);
			Point pt2 = new Point(0,0);
			tricky(pt1, pt2);
			System.out.println("pt1: " + pt1);
			System.out.println("pt2: " + pt2);
			
		}
	}

}
