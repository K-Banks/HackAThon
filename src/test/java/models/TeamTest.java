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
    public void getMembers_returnsTeamMembers_true() {
        Team newTeam = setupNewTeam();
        List<String> expected = new ArrayList<>();
        assertEquals(expected.getClass(), newTeam.getMembers().getClass());
    }

    @Test
    public void getTeamDescription_returnsTeamDescription_true() {
        Team newTeam = setupNewTeam();
        assertTrue(newTeam.getTeamDescription() instanceof String);
    }

    @Test
    public void Team_instantiateNewTeamWithProvidedParameters_notEquals() {
        Team newTeam = setupNewTeam();
        List<String> members = new ArrayList<>();
        members.add("Kyle");
        Team testTeam = new Team(members, "teamName", "this is a description");
        assertNotEquals(newTeam, testTeam);
    }

    @Test
    public void setTeamName_setsTeamNameAsNewString_true() {
        Team newTeam = setupNewTeam();
        String expected = "New Team Name";
        newTeam.setTeamName(expected);
        assertTrue(newTeam.getTeamName().equals(expected));
    }

    @Test
    public void addNewMember_addsNewMemberToMemberListArrayInTeam_true() {
        Team newTeam = setupNewTeam();
        String newMember = "Hambone Fakenamington";
        newTeam.addNewMember(newMember);
        assertTrue(newTeam.getMembers().contains(newMember));
    }

    @Test
    public void getTeams_returnsAllTeamObjects_3() {
        Team newTeam = setupNewTeam();
        Team team2 = setupNewTeam();
        Team team3 = setupNewTeam();
        assertEquals(3, Team.getTeams().size());
    }

    private Team setupNewTeam() {
        return new Team();
    }
}