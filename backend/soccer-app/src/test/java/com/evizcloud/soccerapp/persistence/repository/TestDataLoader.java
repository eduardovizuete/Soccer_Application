package com.evizcloud.soccerapp.persistence.repository;

import com.evizcloud.soccerapp.persistence.model.League;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public class TestDataLoader implements ApplicationContextAware {

    @Autowired 
    ILeagueRepository leagueRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        leagueRepository.save(new League(randomAlphabetic(6), LocalDate.now()));
        leagueRepository.save(new League(randomAlphabetic(6), LocalDate.now()));
        leagueRepository.save(new League(randomAlphabetic(6), LocalDate.now()));
        leagueRepository.save(new League(randomAlphabetic(6), LocalDate.now()));
    }

}