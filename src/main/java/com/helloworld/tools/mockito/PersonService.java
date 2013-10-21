package com.helloworld.tools.mockito;

public class PersonService {

	private final PersonDAO personDao;

	public PersonService(PersonDAO personDao) {
		this.personDao = personDao;
	}

	public boolean update(Integer personId, String name) {
		Person person = personDao.fetchPerson(personId);
		if (person != null) {
			Person updatedPerson = new Person(person.getPersonID(), name);
			personDao.update(updatedPerson);
			return true;
		} else {
			return false;
		}
	}

}
