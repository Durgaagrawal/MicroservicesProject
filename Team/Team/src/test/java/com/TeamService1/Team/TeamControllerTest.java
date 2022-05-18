package com.TeamService1.Team;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.TeamService1.Team.Controller.TeamController;
import com.TeamService1.Team.Entity.Team;
import com.TeamService1.Team.Service.TeamService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TeamController.class)
public class TeamControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TeamService teamService;
	
	Team team=new Team(101,"RCB",8000);
	
	//String exampleTeamJson=
	
	@Test
	public void retrivedetailsforTeam() throws Exception{
		Mockito.when(teamService.fetchTeamByName("RCB")).thenReturn(team);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/team/teamByName/RCB").accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected ="{team_id:1,teamName:RCB,teamMaxBudget:8000}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentType(),false);			
		
	}
}
