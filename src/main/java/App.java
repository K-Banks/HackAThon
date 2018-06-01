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
        List<String> testMembers = new ArrayList<>();
        testMembers.add("LawDog");
        Team testTeam = new Team(testMembers, "I'm the Law, Dog", "ElOhEl");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = Team.getTeams();
            model.put("Team", team);
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
            model.put("manualNav", true);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamName", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String searchTeamName = request.params("teamName");
            Team detailsTeam = Team.getTeamByTeamName(searchTeamName);
            model.put("detailsTeam", detailsTeam);
            return new ModelAndView(model, "teamDetails.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamName/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String searchTeamName = request.params("teamName");
            Team detailsTeam = Team.getTeamByTeamName(searchTeamName);
            model.put("detailsTeam", detailsTeam);
            return new ModelAndView(model, "editTeamDetails.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:teamName", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Team detailsTeam = Team.getTeamByTeamName(request.params("teamName"));
            String newTeamName = request.queryParams("teamName");
            String newTeamDescription = request.queryParams("teamDescription");
            List<String> newMembers = new ArrayList<>();

            newMembers.add(request.queryParams("teamMembers"));
            newMembers.add(request.queryParams("teamMembers2"));
            newMembers.add(request.queryParams("teamMembers3"));
            newMembers.add(request.queryParams("teamMembers4"));
            newMembers.add(request.queryParams("teamMembers5"));

            for (String newMember: newMembers) {
                detailsTeam.addNewMember(newMember);
            }

            detailsTeam.setTeamName(newTeamName);
            detailsTeam.setTeamDescription(newTeamDescription);

            model.put("detailsTeam", detailsTeam);

            return new ModelAndView(model, "teamDetails.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:teamName/addMember", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Team detailsTeam = Team.getTeamByTeamName(request.params("teamName"));
            detailsTeam.addNewMember(request.queryParams("newMember"));
            model.put("detailsTeam", detailsTeam);
            return new ModelAndView(model, "teamDetails.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
