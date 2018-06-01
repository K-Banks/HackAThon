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
        Team testTeam = new Team(newMembers, "I'm the Law, Dog", "ElOhEl");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = Team.getTeams();
//            if (team.size() == 0) {
                model.put("Team", team);
//            }
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newTeamForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> members = new ArrayList<>();

            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            members.add(request.queryParams("teamMembers"));
            members.add(request.queryParams("teamMembers2"));
            members.add(request.queryParams("teamMembers3"));
            members.add(request.queryParams("teamMembers4"));
            members.add(request.queryParams("teamMembers5"));
//            if (members.contains("")) {
//                members.remove("");
//            }

            Team newTeam = new Team(members, teamName, teamDescription);
            model.put("newTeam", newTeam);

            List<Team> team = Team.getTeams();
            model.put("Team", team);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = Team.getTeams();
            model.put("Team", team);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
