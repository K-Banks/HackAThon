import models.Team;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        // Temporary Team Creation for testing
        List<String> newMembers = new ArrayList<>();
        newMembers.add("LawDog");
        Team newTeam = new Team(newMembers, "I'm the Law, Dog", "ElOhEl");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = Team.getTeams();
            if (team.size() > 0) {
                model.put("Team", team);
            }
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
