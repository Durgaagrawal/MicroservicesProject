package com.TeamService1.Team.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TeamService1.Team.Entity.Team;
import com.TeamService1.Team.Repository.TeamRepository;

@Service
public class TeamService {
	
	
	@Autowired
	private TeamRepository teamRepo;
	
	//save all team details
	public Team saveTeam(Team team)
	{
		return teamRepo.save(team);
	}
	
	//fetch team details using team name
	public Team fetchTeamByName(String teamName)
	{
		return teamRepo.findByTeamName(teamName);
	}
	
	//print list of available teams
	public List<String> allTeamName()
	{
		return teamRepo.TeamNames();
	}

	
}
