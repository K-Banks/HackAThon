package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Member> getAll() {
        String sql = "SELECT * FROM members";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public void add(Member newMember) {

    }

    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name, String email, String about, int teamId) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllMembers() {

    }

    @Override
    public void clearAllMembersOfTeam(int teamId) {

    }
}
