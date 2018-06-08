package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String teamName;
    private String teamDescription;

    public Team() {
        teamName = "";
        teamDescription = "";
    }

    public Team(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public void addNewMember(String newMember) {
//        if (!newMember.equals("")) {
//            this.members.add(newMember);
//        }
//    }

    public void setTeamDescription(String newDescription) { this.teamDescription = newDescription; }

}
