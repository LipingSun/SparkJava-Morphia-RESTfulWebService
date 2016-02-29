package edu.sjsu.cmpe282.hw1.dao;

import edu.sjsu.cmpe282.hw1.domain.Employee;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by Liping on 2/29/16.
 */
public class EmployeeDAO extends BasicDAO<Employee, Integer> {
    public EmployeeDAO(Datastore ds) {
        super(ds);
    }

    public List<Employee> getAll() {
        return this.getDatastore().find(Employee.class).asList();
    }

    public void update(Employee employee) {
        this.getDatastore().merge(employee);
    }
}
