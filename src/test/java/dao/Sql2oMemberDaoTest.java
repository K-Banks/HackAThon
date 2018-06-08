package dao;

import models.Member;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import  java.util.List;


import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void getAll_returns0IfNoMembers_0() {
        assertEquals(memberDao.getAll().size(), 0);
    }

    @Test
    public void add_addChangesIdOfMember_1() {
        Member member = setupNewMember();
        memberDao.add(member);
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void findById_returnsMemberWithSpecifiedId() {
        Member member = setupNewMember();
        memberDao.add(member);
        assertEquals(member.getId(), memberDao.findById(1).getId());
    }

    @Test
    public void update_updatesValuesOfMember() {
        Member member = setupNewMember();
        String name = "Jack";
        String about = "huh";
        String email = "jackattack@indabox.com";
        int teamId = 4;
        memberDao.add(member);
        memberDao.update(member.getId(), name, email, about, teamId);
        assertEquals(name, memberDao.findById(member.getId()).getName());
        assertEquals(about, memberDao.findById(member.getId()).getAbout());
        assertEquals(email, memberDao.findById(member.getId()).getEmail());
        assertEquals(teamId, memberDao.findById(member.getId()).getTeamId());
    }

    @Test
    public void deleteById_deletesSpecifiedMember() {
        Member member = setupNewMember();
        Member member2 = setupNewMember();
        Member member3 = setupNewMember();
        memberDao.add(member);
        memberDao.add(member2);
        memberDao.add(member3);
        memberDao.deleteById(member2.getId());
        assertTrue(memberDao.getAll().size() == 2);
        assertFalse(memberDao.getAll().get(1).getId()==member2.getId());
    }

    private Member setupNewMember() {
        return new Member("Kayl", "kayleubanks@gmail.com", "about me", 2);
    }
}