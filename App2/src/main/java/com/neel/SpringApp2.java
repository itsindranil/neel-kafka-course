package com.neel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp2 {

	public static void main(String[] args) {
		//load the application context file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean
		Coach theCoach = context.getBean("myBaseballCoach", Coach.class);
		//call the method
		System.out.println(theCoach.getDailyWorkout());
		
		System.out.println(theCoach.getAssistantCoachAdvise());
		
		//close the context
		context.close();
	}

}
