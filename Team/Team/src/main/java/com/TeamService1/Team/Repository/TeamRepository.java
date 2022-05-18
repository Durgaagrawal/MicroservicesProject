package com.TeamService1.Team.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.TeamService1.Team.Entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
	
	//fetch team details by using teamName
	public Team findByTeamName(String teamName);
	
	//for creating drop down list
	@Query(value="select teamName from Team")
	public List<String> TeamNames();

}
