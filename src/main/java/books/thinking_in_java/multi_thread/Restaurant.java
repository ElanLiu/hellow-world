package books.thinking_in_java.multi_thread;

public class Restaurant {
	
	Order order;
	
	public static void main(String[] args){
		Restaurant restaurant = new Restaurant();
		
		WaitPerson waitPerson = new WaitPerson(restaurant);
		
		Chef chef = new Chef(restaurant, waitPerson);
		
		
	}

}
