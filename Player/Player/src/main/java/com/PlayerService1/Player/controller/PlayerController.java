package com.PlayerService1.Player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.PlayerService1.Player.Entity.Player;
import com.PlayerService1.Player.Entity.Team;
import com.PlayerService1.Player.Exception.ExceedMaxBudgetException;
import com.PlayerService1.Player.Exception.TeamNotExist;
import com.PlayerService1.Player.Service.PlayerService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired 
	private RestTemplate restTeamplate;
	
	@PostMapping("/createPlayer")
	public Player createPlayer(@RequestBody Player player) throws ExceedMaxBudgetException, TeamNotExist
	
	{ 
		//Team teamObj=teamService.fetchTeamByName(player.getPlayerTeamName());
		Team teamObj=this.restTeamplate.getForObject("http://localhost:8090/team/teamByName/"+player.getPlayerTeamName(),Team.class);
		List<Player> playerObj=playerService.FetchPlayerByTeamName(player.getPlayerTeamName());

		if(playerObj.isEmpty() && teamObj!=null)
		{
			if(player.getPlayerBiddingBudget()<=teamObj.getTeamMaxBudget())
			{
			return playerService.createPlayer(player);
			}
			
			else if(player.getPlayerBiddingBudget()>teamObj.getTeamMaxBudget())
			{
				throw new ExceedMaxBudgetException("Player can't be tagged to this team as it's exceeds team's budget");
			}
		}
		else if(playerObj!=null && teamObj!=null)
		{
			int value=playerService.sumOfPlayerBiddingBudget(player.getPlayerTeamName())+player.getPlayerBiddingBudget();
			System.out.println(value);
			if(value>teamObj.getTeamMaxBudget() )
			{
				throw new ExceedMaxBudgetException("Player can't be tagged to this team as it's exceeds team's budget");
			}
			else
			{
				return playerService.createPlayer(player);
			}
		}
		else if(player.getPlayerTeamName().equals("NA"))
		{
			return playerService.createPlayer(player);
		}
		else if(teamObj==null)
		{
			throw new TeamNotExist("Team does not exist");
		}
		
		return player;
	}
	
	//Question3
	@GetMapping("/players/{teamName}")
	public List<Player> fetchPlayers(@PathVariable String teamName)
	{
		return playerService.FetchPlayerByTeamName(teamName);
	}
	
	@GetMapping("/playerDetails/{playerName}")
	public List<Player> fetchPlayer(@PathVariable String playerName) throws Exception
	{
		if(playerService.playerDetails(playerName).isEmpty())
		{
			throw new Exception("playerNot Exist");
			
		}
		else {
			return playerService.playerDetails(playerName);
			
		}
	}
	
	
	
}
