package com.helloworld.tools.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * 
 * @author yu.liuyly
 * 
 * 在软件开发的世界之外, "mock"一词是指模仿或者效仿. 
 * 因此可以将“mock”理解为一个替身，替代者. 在软件开发中提及"mock"，通常理解为模拟对象或者Fake.
 * 
 * 单元测试的思路就是我们想在不涉及依赖关系的情况下测试代码. 
 * 这种测试可以让你无视代码的依赖关系去测试代码的有效性.核心思想就是如果代码按设计正常工作，
 * 并且依赖关系也正常,那么他们应该会同时工作正常.
 * 
 * 下面的例子：
 * service的代码已经准备完毕，可是dao还没有完成，或者我们不想真正的和数据库交互。
 * 所以需要mock dao的代码，让service看似在调用dao，获取/更新数据库。
 * 
 * 所以我们的工作就是在单元/接口测试中，尽量的隔离不需要校验的接口/实现/代码，来focus在我们需要校验的代码上。
 * 
 * 步骤：
 * 1 使用mock来模拟对象，可以通过注解或者代码方式
 * 2 设定返回值/测试数据....when-thenreturn的方式
 * 
 * 参考：
 * http://hotdog.iteye.com/category/143340
 * http://docs.mockito.googlecode.com/hg/org/mockito/Mockito.html#RETURNS_SMART_NULLS
 * http://www.oschina.net/translate/mockito-a-great-mock-framework-for-java-development
 *
 */
public class PersonServiceTest {

	//通过注解，指定需要mock的对象.你不仅可以模拟接口,任何具体类都行
	@Mock
	private PersonDAO personDAO;
	private PersonService personService;

	@Before
	public void setUp() throws Exception {
		//只有Annotation还不够，要让它们工作起来还需要进行初始化工作。
		MockitoAnnotations.initMocks(this);
		personService = new PersonService(personDAO);
	}

	@Test
	public void shouldUpdatePersonName() {
		//除了通过注解指定mock对象，也可以通过编码方式。建议使用注解方式。
		//PersonDAO personDAO = mock(PersonDAO.class);
		
		Person person = new Person(1, "Phillip");
		
		//执行前准备测试数据。设定stub，一旦通过personDAO调用fetchPerson方法，就返回指定的对象person
		when(personDAO.fetchPerson(1)).thenReturn(person);
		
		//也可以在方法调用过程中返回异常
		//when(personDAO.fetchPerson(1)).thenThrow( new  RuntimeException());
		
		//也可以不对参数有限制
		//when(personDAO.fetchPerson(anyInt())).thenReturn( person );
		
		//对于返回void的函数，使用doNothing
		//doNothing().when(personDAO).fetchPerson(anyInt());
		
		//也可以使得调用成为一个迭代器，按照顺序，依次返回我们需要的结果。
		/*
		 * 第一次调用，返回runtime异常，
		 * 第二次调用，返回person
		 * when(personDAO.fetchPerson(1))
		   .thenThrow(new RuntimeException())
		   .thenReturn(person);
		 */

		
		boolean updated = personService.update(1, "David");
		assertTrue(updated);
		
		//Mockito中用 verify(…).methodXxx(…) 语法来验证 methodXxx方法是否按照预期进行了调用
		//验证方法调用次数: 检验personDAO.fetchPerson方法是否被调用过times次。
		//也可以不写times(1),默认就是1次
		verify(personDAO, times(1)).fetchPerson(1);
		
		//被调用最少2次、最多2次，从没有被调用
		//verify(personDAO, atLeast(2)).fetchPerson(1);
		//verify(personDAO, atMost(2)).fetchPerson(1);
		//verify(personDAO, never()).fetchPerson(1);
		
		//可以用参数捕获器来捕获传入方法的参数进行验证，看它是否符合我们的要求。 
		ArgumentCaptor<Person> personCaptor = ArgumentCaptor
				.forClass(Person.class);
		//capture()捕获到上面输入的参数：person
		verify(personDAO).update(personCaptor.capture());
		//根据参数person，拿到Person对象，在拿到name或者id
		Person updatedPerson = personCaptor.getValue();
		//校验根据参数传入后得到的结果值，是否和预期匹配。
		//其实这里的对象值是已知的（name=david，是我们赋值的），可以直接通过assert来断言，没有必要多此一举
		assertEquals("David", updatedPerson.getPersonName());
		// asserts that during the test, there are no other calls to the mock
		// object.不建议每个测试方法中都使用该校验
		verifyNoMoreInteractions(personDAO);
		
		/*
		 * 对于需要mock对个对象，并且有调用顺序的方法校验，可以通过InOrder来模拟。
		 */
		/*InOrder inOrder = new InOrder(firstPerson, secondPerson);
		inOrder.verify(firstPerson).update(1, "first");
		inOrder.verify(secondPerson).update(1, "second");*/
	}

	@Test
	public void shouldNotUpdateIfPersonNotFound() {
		when(personDAO.fetchPerson(1)).thenReturn(null);
		boolean updated = personService.update(1, "David");
		assertFalse(updated);
		verify(personDAO).fetchPerson(1);
		verifyZeroInteractions(personDAO);
		verifyNoMoreInteractions(personDAO);
	}

}
