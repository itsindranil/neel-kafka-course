package com.neel;

public class CricketCoach implements Coach{
	
	public CricketCoach() {
		
	}
	
	//private field for the service
	private AssistantCoachService asstcoachService;



	//defining a constructor for dependency injection
	public CricketCoach(AssistantCoachService new_asstcoachService) {
		asstcoachService = new_asstcoachService;
	}
	
	public String getDailyWorkout() {
		return "Practice batting for 30 mins.";
	}

	@Override
	public String getAssistantCoachAdvise() {
		return asstcoachService.additionalWorkout() + " with some fielding and catching.";
	}

}
