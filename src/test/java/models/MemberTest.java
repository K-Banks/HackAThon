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

    @Test
    public void setName_setsNewNameForMember() {
        Member member = setupNewMember();
        member.setName("John");
        assertFalse(member.getName().equals("Kayl"));
    }

    @Test
    public void setEmail_setsNewEmailForMember() {
        Member member = setupNewMember();
        member.setEmail("email");
        assertFalse(member.getEmail().equals("kayleubanks@gmail.com"));
    }

    @Test
    public void setAbout_setsNewAboutForMember() {
        Member member = setupNewMember();
        member.setAbout("meh");
        assertFalse(member.getAbout().equals("about me"));
    }

    private Member setupNewMember() {
        return new Member("Kayl", "kayleubanks@gmail.com", "about me", 2);
    }
}