package com.helloworld.tools.mockito;

public interface PersonDAO {
	public Person fetchPerson(Integer personID);

	public void update(Person person);
	
	public void doSomething();

}
