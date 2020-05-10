package com.evizcloud.soccerapp;

import com.evizcloud.soccerapp.service.ILeagueService;
import com.evizcloud.soccerapp.service.ITeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SoccerApp {

	private static final Logger LOG = LoggerFactory.getLogger(SoccerApp.class);

	@Autowired
	ILeagueService leagueService;

	@Autowired
	ITeamService teamService;

	public static void main(String[] args) {
		SpringApplication.run(SoccerApp.class, args);
	}

	@PostConstruct
	public void postConstruct() {
		try {
			leagueService.createProjectWithTasks();
		} catch (Exception e) {
			LOG.error("Error occurred in creating project with tasks", e);
		}

		LOG.info("Fetching all Leagues");
		leagueService.findAll()
				.forEach(p -> LOG.info(p.toString()));

		LOG.info("Fetching all teams");
		teamService.findAll()
				.forEach(t -> LOG.info(t.toString()));
	}

}
