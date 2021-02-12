package com.neel;

public class BaseballCoach implements Coach {
	public BaseballCoach() {
		System.out.println("inside no-arg Constructor");
	}

	private AssistantCoachService asstcoachService;
	private String email_address;
	private String team;
	
	
	
	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}


	

	public void setAsstcoachService(AssistantCoachService asstcoachService) {
		System.out.println("Inside Setter Method");
		this.asstcoachService = asstcoachService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice Home runs";
	}

	@Override
	public String getAssistantCoachAdvise() {
		return asstcoachService.additionalWorkout() + " Try to hit the ball out of the park";
	}

}
