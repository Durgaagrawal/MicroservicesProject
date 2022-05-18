package com.TeamService1.Team.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TeamService1.Team.Entity.Team;
import com.TeamService1.Team.Exception.TeamNameAlreadyexistException;
import com.TeamService1.Team.Service.TeamService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	
	@PostMapping("/createTeam")
	public Team saveTeam(@RequestBody Team team) throws TeamNameAlreadyexistException 
	{
		Team teamObj=teamService.fetchTeamByName(team.getTeamName());
		if(teamObj!=null)
		{
			throw new TeamNameAlreadyexistException("Team Name already exist");
		}
		else
		{
			teamService.saveTeam(team);
		}
		return team;
	}
	
	@GetMapping("/teamByName/{teamName}")
	public Team fetchTeamByName(@PathVariable String teamName)
	{
		return teamService.fetchTeamByName(teamName);
	}
	

	@GetMapping("/teamName")
	public List<String> teamNames()
	{
		return teamService.allTeamName();
	}
	
	

}
