package dao;

import models.Team;

import java.util.List;

public interface TeamDao {
    //Get All
    List<Team> getAll();

    // CREATE
    void add(Team developer);

    // READ
    Team findById(int id);

    //UPDATE
    void update(int id, String name);

    // DELETE
    void deleteById(int id);
    void clearAllDevelopers();
}
