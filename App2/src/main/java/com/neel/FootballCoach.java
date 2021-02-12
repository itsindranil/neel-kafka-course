package com.neel;

public class FootballCoach implements Coach {
	
	public FootballCoach() {
		
	}
	
	//private field for the service
	private AssistantCoachService asstcoachService;
	
	
	//defining a constructor for dependency injection
	public FootballCoach(AssistantCoachService new_asstcoachService) {
		asstcoachService = new_asstcoachService;
	}

	public String getDailyWorkout() {
		return "Try penalty kicks for 30 mins";
	}

	@Override
	public String getAssistantCoachAdvise() {
		return asstcoachService.additionalWorkout() + " with studs high and hamstrings";
	}
}
