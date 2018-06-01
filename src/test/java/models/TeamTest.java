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
        Team.getTeams().clear();
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
        assertTrue(Team.getTeams().contains(newTeam));
        assertTrue(Team.getTeams().contains(team2));
        assertTrue(Team.getTeams().contains(team3));
        assertEquals(3, Team.getTeams().size());
    }

    @Test
    public void getTeamByTeamName_returnsTeamObjectWithSameNameAsSubmitted_true() {
        Team newTeam = setupNewTeam();
        Team team2 = setupNewTeam();
        Team team3 = setupNewTeam();
        List<String> members = new ArrayList<>();
        members.add("Kyle");
        Team testTeam = new Team(members, "teamName", "this is a description");
        Team testTeam2 = new Team(members, "THISISATEAMNAME", "huh?");
        Team expectedTeam1 = Team.getTeamByTeamName("teamName");
        Team expectedTeam2 = Team.getTeamByTeamName("THISISATEAMNAME");
        assertEquals(testTeam, expectedTeam1);
        assertEquals(testTeam2, expectedTeam2);
    }

    @Test
    public void setTeamDescription_setsNewTeamDescription_equalsTrue() {
        Team newTeam = setupNewTeam();
        String expected = "new Team Description";
        newTeam.setTeamDescription(expected);
        assertTrue(newTeam.getTeamDescription().equals(expected));
    }

    @Test
    public void removeMember_removesMemberOfTeam_true() {
        Team newTeam = setupNewTeam();
        newTeam.addNewMember("steven");
        newTeam.addNewMember("carl");
        newTeam.addNewMember("circus");
        newTeam.removeMember("circus");
        assertEquals(2, newTeam.getMembers().size());
    }

    @Test
    public void updateMember_updatesMemberOfTeam_true() {
        Team newTeam = setupNewTeam();
        newTeam.addNewMember("steven");
        newTeam.addNewMember("carl");
        newTeam.addNewMember("serkis");
        newTeam.updateMember("andy", 2);
        assertTrue(newTeam.getMembers().contains("andy"));
        assertEquals(3, newTeam.getMembers().size());
    }

    private Team setupNewTeam() {
        return new Team();
    }
}