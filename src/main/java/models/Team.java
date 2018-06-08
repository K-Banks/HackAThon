package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {
    private int id;
    private String name;
    private String description;

    public Team() {
        name = "";
        description = "";
    }

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
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



    public void setDescription(String newDescription) { this.description = newDescription; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) &&
                Objects.equals(description, team.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description);
    }
}
