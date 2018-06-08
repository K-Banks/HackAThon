package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
    @Test
    public void instantiatesNewMember() {
        Member newMember = setupNewMember();
        assertTrue(newMember instanceof Member);
    }

    @Test
    public void setId_setsNewIdValueForMember() {
        Member member = setupNewMember();
        int originalId = member.getId();
        int newId = 3;
        member.setId(newId);
        assertFalse(originalId==member.getId());
    }

    private Member setupNewMember() {
        return new Member("Kayl", "kayleubanks@gmail.com", "about me", 2);
    }
}