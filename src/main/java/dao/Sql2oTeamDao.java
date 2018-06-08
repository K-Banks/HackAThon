package dao;

import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Team> getAll() {
        String sql = "SELECT * FROM teams";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Team.class);
        }
    }

    @Override
    public void add(Team newTeam) {
        String sql = "INSERT INTO teams (name, description) VALUES (:name, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(newTeam)
                    .executeUpdate()
                    .getKey();
            newTeam.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Team findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name, String description) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllTeams() {

    }
}
