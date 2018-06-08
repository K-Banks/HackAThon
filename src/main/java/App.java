import models.*;
import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/hack-a-thon.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        // Routing for homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = teamDao.getAll();
            model.put("Team", team);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Routing for new team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newTeamForm.hbs");
        }, new HandlebarsTemplateEngine());

        // Routing for teams page using nav tab
        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            model.put("manualNav", true);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Dynamic routing for team details
        get("/teams/:teamId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int searchTeamId = Integer.parseInt(request.params("teamId"));
            Team detailsTeam = teamDao.findById(searchTeamId);
            model.put("detailsTeam", detailsTeam);
            List<Member> membersOfTeam = memberDao.findByTeamId(searchTeamId);
            model.put("members", membersOfTeam);
            return new ModelAndView(model, "teamDetails.hbs");
        }, new HandlebarsTemplateEngine());

        // Routing for editing team details form
        get("/teams/:teamId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int searchTeamId = Integer.parseInt(request.params("teamId"));
            Team detailsTeam = teamDao.findById(searchTeamId);
            model.put("detailsTeam", detailsTeam);
            List<Member> members = memberDao.findByTeamId(searchTeamId);
            model.put("members", members);
            return new ModelAndView(model, "editTeamDetails.hbs");
        }, new HandlebarsTemplateEngine());

        //Routing for about page
        get("/about", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

//        //Routing for removing a team member
//        get("/teams/:team/removeMember/:member", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Team detailsTeam = Team.getTeamByTeamName(request.params("team"));
//            detailsTeam.removeMember(request.params("member"));
//            model.put("detailsTeam", detailsTeam);
//            return new ModelAndView(model, "editTeamDetails.hbs");
//        }, new HandlebarsTemplateEngine());

        //Routing to remove a team
        get("/teams/:teamId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int deleteTeamId = Integer.parseInt(request.params("teamId"));
            teamDao.deleteById(deleteTeamId);
            response.redirect("/teams");
            return null;
        }, new HandlebarsTemplateEngine());
//
        //Form submission for new teams
        post("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            String memberName = request.queryParams("memberName");
            String memberEmail = request.queryParams("memberEmail");
            String memberAbout = request.queryParams("memberAbout");

            if (teamName.equals("") || memberName.equals("") || memberEmail.equals("") || !memberEmail.contains("@")){
                model.put("invalidSubmission", true);
                return new ModelAndView(model, "success.hbs");
            } else {
                Team newTeam = new Team(teamName, teamDescription);
                teamDao.add(newTeam);
                Member newMember = new Member(memberName, memberEmail, memberAbout, newTeam.getId());
                memberDao.add(newMember);
            }

            response.redirect("/teams");
            return null;
        }, new HandlebarsTemplateEngine());

        // Form submission for team detail edits
        post("/teams/:teamId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            String newName = request.queryParams("teamName");
            String newDescription = request.queryParams("teamDescription");

            if (newName.equals("")) {
                model.put("invalidSubmission", true);
                return new ModelAndView(model,"success.hbs");
            } else {
                teamDao.update(teamId, newName, newDescription);
            }

            response.redirect("/teams/" + teamId);
            return null;
        }, new HandlebarsTemplateEngine());

//        //Form submission for single member addition to a team
//        post("/teams/:teamName/addMember", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Team detailsTeam = Team.getTeamByTeamName(request.params("teamName"));
//            detailsTeam.addNewMember(request.queryParams("newMember"));
//            model.put("detailsTeam", detailsTeam);
//            return new ModelAndView(model, "teamDetails.hbs");
//        }, new HandlebarsTemplateEngine());

        //GET member details page

        //POST new member from team details page

        //POST member edit

        //GET member delete
    }
}
