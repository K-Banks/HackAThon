package dao;
import models.Member;

import java.util.List;

public interface MemberDao {

    //Get All
    List<Member> getAll();

    // CREATE
    void add(Member newMember);

    // READ
    Member findById(int id);

    //UPDATE
    void update(int id, String name, String email, String about, int teamId);

    // DELETE
    void deleteById(int id);

    void clearAllMembers();

    void clearAllMembersOfTeam(int teamId);
}
