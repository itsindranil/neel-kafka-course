package com.neel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp3 {

	public static void main(String[] args) {
			//load the application context file
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			//get the bean
			BaseballCoach theCoach = context.getBean("myBaseballCoach", BaseballCoach.class);
			//call the method
			System.out.println(theCoach.getDailyWorkout());
			
			System.out.println(theCoach.getEmail_address());
			
			System.out.println(theCoach.getTeam());
			
			//close the context
			context.close();
		}

	}
