package com.evizcloud.soccerapp;

import com.evizcloud.soccerapp.persistence.model.League;
import com.evizcloud.soccerapp.service.ILeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class SoccerApp {

	private static final Logger LOG = LoggerFactory.getLogger(SoccerApp.class);

	@Autowired
	private ILeagueService leagueService;

	public static void main(String[] args) {
		SpringApplication.run(SoccerApp.class, args);
	}

	@PostConstruct
	public void postConstruct() {
		leagueService.save(new League("First League", LocalDate.now()));
		leagueService.save(new League("Second League", LocalDate.now()));
		leagueService.save(new League("Third League", LocalDate.now()));
		leagueService.save(new League("Fourth League", LocalDate.now()));
	}

}
