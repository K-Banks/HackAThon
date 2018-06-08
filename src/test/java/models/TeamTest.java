package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Team_instatiatesNewTeam_true() {
        Team newTeam = setupNewTeam();
        assertTrue(newTeam instanceof Team);
    }

    @Test
    public void getTeamName_returnsTeamName_true() {
        Team newTeam = setupNewTeam();
        assertTrue(newTeam.getTeamName() instanceof String);
    }

    @Test
    public void getTeamDescription_returnsTeamDescription_true() {
        Team newTeam = setupNewTeam();
        assertTrue(newTeam.getTeamDescription() instanceof String);
    }

    @Test
    public void setTeamName_setsTeamNameAsNewString_true() {
        Team newTeam = setupNewTeam();
        String expected = "New Team Name";
        newTeam.setTeamName(expected);
        assertTrue(newTeam.getTeamName().equals(expected));
    }

    @Test
    public void setTeamDescription_setsNewTeamDescription_equalsTrue() {
        Team newTeam = setupNewTeam();
        String expected = "new Team Description";
        newTeam.setTeamDescription(expected);
        assertTrue(newTeam.getTeamDescription().equals(expected));
    }

    @Test
    public void setId_setsNewIdForTeam() {
        Team newTeam = setupNewTeam();
        int expected = 3;
        int original = newTeam.getId();
        newTeam.setId(expected);
        assertNotEquals(original, newTeam.getId());
        assertEquals(expected, newTeam.getId());
    }

    private Team setupNewTeam() {
        return new Team();
    }
}