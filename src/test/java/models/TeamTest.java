package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Team newTeam = new Team();
        assertTrue(newTeam instanceof Team);
    }
}