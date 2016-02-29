package edu.sjsu.cmpe282.hw1.dao;

import edu.sjsu.cmpe282.hw1.domain.Project;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Liping on 2/29/16.
 */
public class ProjectDAO extends BasicDAO<Project, Integer> {
    public ProjectDAO(Datastore ds) {
        super(ds);
    }

    public List<Project> getAll() {
        return this.getDatastore().find(Project.class).asList();
    }

    public void update(Project project) {
        this.getDatastore().merge(project);
    }
}
