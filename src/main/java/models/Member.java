package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member {
    private int id;
    private String name;
    private String email;
    private String about;
    private int teamId;

    public Member(String name, String email, String about, int teamId) {
        this.name = name;
        this.email = email;
        this.about = about;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return teamId == member.teamId &&
                Objects.equals(name, member.name) &&
                Objects.equals(email, member.email) &&
                Objects.equals(about, member.about);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, email, about, teamId);
    }
}
