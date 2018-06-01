package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<String> members;
    private String teamName;
    private String teamDescription;

    public Team() {
        members = new ArrayList<>();
        teamName = "";
        teamDescription = "";
    }

    public Team(List<String> members, String teamName, String teamDescription) {
        this.members = members;
        this.teamName = teamName;
        this.teamDescription = teamDescription;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
