package edu.sjsu.cmpe282.hw1.rest;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import edu.sjsu.cmpe282.hw1.dao.ProjectDAO;
import edu.sjsu.cmpe282.hw1.domain.Project;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static spark.Spark.halt;

/**
 * Created by Liping on 2/26/16.
 */
public class ProjectResource {

    private static final Morphia morphia = new Morphia();
    private static final Datastore datastore = morphia.createDatastore(new MongoClient(), "cmpe282LipingSun391");
    private static final Gson gson = new Gson();

    private static final ProjectDAO projectDAO = new ProjectDAO(datastore);

    public static Project get(Request req, Response res) {
        Project project = projectDAO.get(Integer.valueOf(req.params(":id")));
        if (project == null) {
            halt(HttpServletResponse.SC_NOT_FOUND);
        }
        res.status(HttpServletResponse.SC_OK);
        res.type("application/json");
        return project;
    }

    public static String create(Request req, Response res) {
        Project project = gson.fromJson(req.body(), Project.class);
        if (projectDAO.exists("id", project.getId())) {
            halt(HttpServletResponse.SC_CONFLICT);
        } else {
            res.status(HttpServletResponse.SC_CREATED);
            projectDAO.save(project);
        }
        return "";
    }

    public static List<Project> getAll(Request req, Response res) {
        List<Project> projects = projectDAO.getAll();
        if (projects.size() == 0) {
            res.status(HttpServletResponse.SC_NOT_FOUND);
        } else {
            res.status(HttpServletResponse.SC_OK);
        }
        res.type("application/json");
        return projects;
    }

    public static String update(Request req, Response res) {
        Project project = gson.fromJson(req.body(), Project.class);
        project.setId(Integer.parseInt(req.params(":id")));
        try {
            projectDAO.update(project);
        } catch (Exception e) {
            halt(HttpServletResponse.SC_NOT_FOUND);
        }
        res.status(HttpServletResponse.SC_OK);
        return "";
    }

    public static String delete(Request req, Response res) {
        WriteResult writeResult = projectDAO.deleteById(Integer.parseInt(req.params(":id")));
        if (writeResult.getN() == 0) {
            halt(HttpServletResponse.SC_NOT_FOUND);
        }
        res.status(HttpServletResponse.SC_OK);
        return "";
    }
}
