package books.practical_java;

/*
 * Java 提供instanceof 操作符，作为在运行期确定[某个对象隶属哪个class]
的机制。就像其他语言特性一样，instanceof 经常被滥用。你可以通过多态
(polymorphism)来避免许多常见instanceof 滥用情况。这不仅可以提高程序
的面向对象纯度，还可以使程序拥有较好的扩充性。
 */
public class NotInstanceof {
	
	public interface Employee{
		public int salary();
	}
	
	public class Manager implements Employee{
		@Override
		public int salary() {
			return 10;
		}
	}
	
	public class Programmer implements Employee{
		@Override
		public int salary() {
			return 10;
		}
		
		public int bonus(){
			return 20;
		}
	}
	
	//滥用instanceof，因为接口/类设计的不合理，而导致必须使用instanceof来弥补
	public class Payroll{
		public int calcPayroll(Employee e){
			if(e instanceof Programmer){
				return ((Programmer)e).bonus() + ((Programmer)e).salary();
			}
			return e.salary();
		}
	}
	
	
	//=======================重新设计==============================
	public interface Employee2{
		public int salary();
		public int bonus();
	}
	
	public class Manager2 implements Employee2{
		@Override
		public int salary() {
			return 10;
		}
		public int bonus(){
			return 0;
		}
	}
	
	public class Programmer2 implements Employee2{
		@Override
		public int salary() {
			return 10;
		}
		
		public int bonus(){
			return 20;
		}
	}
	
	public class Payroll2{
		public int calcPayroll(Employee2 e){
			return e.salary() + e.bonus();
		}
	}

}
