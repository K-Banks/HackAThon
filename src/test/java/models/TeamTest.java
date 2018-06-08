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
    public void getName_returnsName_true() {
        Team newTeam = setupNewTeam();
        assertTrue(newTeam.getName() instanceof String);
    }

    @Test
    public void getDescription_returnsDescription_true() {
        Team newTeam = setupNewTeam();
        assertTrue(newTeam.getDescription() instanceof String);
    }

    @Test
    public void setName_setsNameAsNewString_true() {
        Team newTeam = setupNewTeam();
        String expected = "New Team Name";
        newTeam.setName(expected);
        assertTrue(newTeam.getName().equals(expected));
    }

    @Test
    public void setDescription_setsNewDescription_equalsTrue() {
        Team newTeam = setupNewTeam();
        String expected = "new Team Description";
        newTeam.setDescription(expected);
        assertTrue(newTeam.getDescription().equals(expected));
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