package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
    @Test
    public void instantiatesNewMember() {
        Member newMember = new Member("Kayl", "kayleubanks@gmail.com", "about me", 2);
        assertTrue(newMember instanceof Member);
    }
}