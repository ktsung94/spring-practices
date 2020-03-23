package com.douzone.container.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.douzone.container.user.User;
import com.douzone.container.user.User1;

public class XmlConfig {

	public static void main(String[] args) {
		//testBeanFatory01();
		//testBeanFatory02();
		//		TestApplicationContext01();



		TestApplicationContext02();
	}

	// XML Bean Configuration(Implicit Configuration)
	public static void TestApplicationContext02() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("config/user/applicationContext02.xml");

		// id로 빈 가져오기
		User user = (User) appContext.getBean("user");
		System.out.println(user);

		// name으로 빈 가져오기
		user = (User) appContext.getBean("usr");
		System.out.println(user);

		// type으로 빈 가져오기
		// 같은 타입의 객체가 2개 이상 있는 경우 type으로만 가져오면 에러! user = (User) appContext.getBean(User.class); <- 에러
		// type + id 또는 type + name
		user = appContext.getBean("user2", User.class);
		System.out.println(user);

		// 2개 파라미터 생성자로 생성한 빈 가져오기
		user = appContext.getBean("user3", User.class);
		System.out.println(user);
		
		// setter을 사용한 빈 가져오기
		user = appContext.getBean("user5", User.class);
		System.out.println(user);
		
		user = appContext.getBean("user6", User.class);
		System.out.println(user);
		
		user = appContext.getBean("user7", User.class);
		System.out.println(user);
		
		// 자원정리
		((ConfigurableApplicationContext)appContext).close();
	}

	// XML Auto-Configuration(Annotation Scanning)
	public static void TestApplicationContext01() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("config/user/applicationContext01.xml");

		User1 user1 = appContext.getBean(User1.class);
		System.out.println(user1.getName());

		// Bean의 id가 자동으로 설정됨
		// id에 스트링 타입을 주면 무슨타입인지 모르기 때문에 캐스팅을 해줘야한다.
		user1 = (User1)appContext.getBean("user1");	// id = user1
		System.out.println(user1.getName());
	}

	//	// XML Bean Configuration(Implicit Configuration)
	//	public static void TestApplicationContext02() {
	//		ApplicationContext appContext = new ClassPathXmlApplicationContext("config/user/applicationContext02.xml");
	//
	//		User1 user1 = appContext.getBean(User1.class);
	//		System.out.println(user1.getName());
	//
	//		// Bean설정에서는 Bean의 id가 자동으로 설정되지 않는다.
	//		// 명시적으로 설정해야한다.
	//		// <bean id="user1" class="com.douzone.container.user.User1" />
	//		// 만약 id를 써주지 않으면 오류가 난다.
	//		user1 = (User1)appContext.getBean("user1");	// id = user1
	//		System.out.println(user1.getName());
	//
	//		// 자원정리
	//		((ConfigurableApplicationContext)appContext).close();
	//	}

	// XML Auto-Configuration(Annotation Scanning)
	public static void testBeanFatory01() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/user/applicationContext01.xml"));

		User1 user1 = bf.getBean(User1.class);
		System.out.println(user1.getName());

		// Bean의 id가 자동으로 설정됨
		// id에 스트링 타입을 주면 무슨타입인지 모르기 때문에 캐스팅을 해줘야한다.
		user1 = (User1)bf.getBean("user1");	// id = user1
		System.out.println(user1.getName());
	}

	// XML Bean Configuration(Implicit Configuration)
	public static void testBeanFatory02() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/user/applicationContext02.xml"));

		User1 user1 = bf.getBean(User1.class);
		System.out.println(user1.getName());

		// Bean설정에서는 Bean의 id가 자동으로 설정되지 않는다.
		// 명시적으로 설정해야한다.
		// <bean id="user1" class="com.douzone.container.user.User1" />
		// 만약 id를 써주지 않으면 오류가 난다.
		user1 = (User1)bf.getBean("user1");	// id = user1
		System.out.println(user1.getName());
	}


}
