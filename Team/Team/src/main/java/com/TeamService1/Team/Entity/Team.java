package com.TeamService1.Team.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int team_id;
	private String teamName;
	private int teamMaxBudget;
	
	//constructor
	public Team(int team_id, String teamName, int teamMaxBudget) {
		super();
		this.team_id = team_id;
		this.teamName = teamName;
		this.teamMaxBudget = teamMaxBudget;
	}
	//no argument constructor
	public Team() {
		super();
	}
	
	//getters and setters
	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamMaxBudget() {
		return teamMaxBudget;
	}

	public void setTeamMaxBudget(int teamMaxBudget) {
		this.teamMaxBudget = teamMaxBudget;
	}
	
	
	
	

}
