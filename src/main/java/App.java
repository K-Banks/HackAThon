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

        //Routing for removing a team member
        get("/teams/:teamId/member/:memberId/delete", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            memberDao.deleteById(memberId);
            response.redirect("/teams/"+teamId+"/edit");
            return null;
        }, new HandlebarsTemplateEngine());

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
        post("/teams/add", (request, response) -> {
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

        //Form submission for single member addition to a team
        post("/teams/:teamId/members/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            String name = request.queryParams("memberName");
            String email = request.queryParams("memberEmail");
            String about = request.queryParams("memberAbout");

            if (name.equals("") || !email.contains("@")) {
                model.put("invalidSubmission", true);
                return new ModelAndView(model, "success.hbs");
            } else {
                Member newMember = new Member(name, email, about, teamId);
                memberDao.add(newMember);

                response.redirect("/teams/" + teamId + "/members/add");
                return null;
            }
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

        //GET member details page
        get("/teams/:teamId/members/:memberId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            Team team = teamDao.findById(teamId);
            Member member = memberDao.findById(memberId);
            model.put("team", team);
            model.put("member", member);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //GET member add form from team details page
        get("/teams/:teamId/members/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            List<Member> members = memberDao.findByTeamId(teamId);
            model.put("members", members);
            Team team = teamDao.findById(teamId);
            model.put("team", team);
            return new ModelAndView(model, "new-member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //POST member edit
        post("/teams/:teamId/members/:memberId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            String name = request.queryParams("memberName");
            String email = request.queryParams("memberEmail");
            String about = request.queryParams("memberAbout");

            if (name.equals("") || !email.contains("@")) {
                model.put("team", teamDao.findById(teamId));
                model.put("member", memberDao.findById(memberId));
                return new ModelAndView(model, "success.hbs");
            } else {
                memberDao.update(memberId, name, email, about, teamId);
                response.redirect("/teams/" + teamId + "/members/" + memberId);
                return null;
            }
        }, new HandlebarsTemplateEngine());

        //GET member edit form
        get("/teams/:teamId/members/:memberId/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            model.put("member", memberDao.findById(memberId));
            model.put("edit", true);
            model.put("team", teamDao.findById(teamId));
            return new ModelAndView(model, "new-member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //GET member delete
        get("/teams/:teamId/members/:memberId/delete", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            memberDao.deleteById(memberId);
            response.redirect("/teams/"+teamId);
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
