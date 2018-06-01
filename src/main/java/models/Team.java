package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<String> members;
    private String teamName;
    private String teamDescription;
    private static List<Team> teams = new ArrayList<>();

    public Team() {
        members = new ArrayList<>();
        teamName = "";
        teamDescription = "";
        teams.add(this);
    }

    public Team(List<String> members, String teamName, String teamDescription) {
        this.members = members;
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        teams.add(this);
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

    public void addNewMember(String newMember) {
        if (!newMember.equals("")) {
            this.members.add(newMember);
        }
    }

    public void setTeamDescription(String newDescription) { this.teamDescription = newDescription; }

    public static List<Team> getTeams() {
        return teams;
    }

    public static Team getTeamByTeamName(String searchTeamName) {
        int teamIndex = 0;
        for (Team team : teams) {
            if (team.teamName.equals(searchTeamName)) {
                break;
            }
            teamIndex ++;
        }
        return teams.get(teamIndex);
    }

    public void removeMember(String memberToRemove) {
        members.remove(memberToRemove);
    }

    public void updateMember(String updatedName, int positionInArray) {
        members.set(positionInArray, updatedName);
    }
}
