package books.practical_java;

import books.practical_java.PassByValue.Point;

/*
 * int i = 5; //Primitive type
Integer j = new Integer(10); //Object reference
 * 这两个变量都存储在局部变量表，它们的操作都在Java 操作数堆栈
（operand stack）中进行，但二者所表述的意义完全不同（请注意，稍后讨
论将以术语stack 表示[操作数堆栈]或[局部变量表]）。不论是基本型别int
基本型别（Primitive types） 外覆类（Wrapper class）
boolean Boolean
char Character
byte Byte
short Short
int Integer
long Long
float Float
double Double
或object reference，它们都在stack 中占据32 bits 空间，但Intege 对象在stack
中记录的并不是对象本身，而是对象的reference。

所有Java 对象都是通过Object references 来访问的，那是某种形式的指针，
该指针指向heap 中的某块区域，heap 则为对象的生存提供了真实存储场所。
当你声明了一个基本型别后，你就为它声明了一份存储空间。前述两行代
码可以这样表示：

  Stack                             Heap
5//i’s value
j   ------------------------------Integer 10


primitive存在栈中，reference 存在堆中，栈中存的是引用。
 */
public class Assign {
	
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj != null && this.getClass() == obj.getClass()){
			return true;
		}
		return true;
	}
	
	
	public static class inner {
		public static void main(String[] args) {
			
			//primitive, stack
			int i = 10;
			
			int j = 11;
			
			//reference, 声明存在stack中，实际的对象存在heap中
			Integer in1 = new Integer(10);
			
			Integer in2 = new Integer(12); 
			
			i = j;
			
			j = 22;
			
			System.out.println("i= " + i + ", j=" + j);
			
			in1 = in2;
			in2 = in2.valueOf(222);
			in1.equals(obj)
			System.out.println("in1= " + in1 + ", in2=" + in2);
			
			
		}
	}

}
