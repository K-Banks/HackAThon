package dao;

import models.Team;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import  java.util.List;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add_setsId_1() {
        Team newTeam = setUpNewTeam();
        teamDao.add(newTeam);
        assertEquals(1, newTeam.getId());
    }

    @Test
    public void getAll_returnsEmptyList() {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAll_returnsAllTeams_2() {
        Team newTeam = setUpNewTeam();
        Team newTeam2 = setUpNewTeam();
        teamDao.add(newTeam);
        teamDao.add(newTeam2);
        assertEquals(2, teamDao.getAll().size());
        assertTrue(newTeam.getName().equals(teamDao.getAll().get(0).getName()));
    }

    @Test
    public void findById_returnsTeamWithCorrectId_true() {
        Team newTeam = setUpNewTeam();
        Team team2 = new Team("Monstars", "space jam");
        teamDao.add(newTeam);
        teamDao.add(team2);
        Team foundTeam = teamDao.findById(2);
        assertEquals(foundTeam.getName(), team2.getName());
    }

    private Team setUpNewTeam() {
        return new Team("teamName", "teamDescription");
    }
}