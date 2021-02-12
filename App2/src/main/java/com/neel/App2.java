package com.neel;

public class App2 {

	public static void main(String[] args) {
		Coach football_coach = new FootballCoach();
		//Coach cricket_coach = new CricketCoach();
		
		System.out.println(football_coach.getDailyWorkout());
		System.out.println("\n");
		System.out.println(football_coach.getAssistantCoachAdvise());  // Not sure why this is giving error

	}

}
