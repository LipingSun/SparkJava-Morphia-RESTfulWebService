package edu.sjsu.cmpe282.hw1.rest;

import com.google.gson.Gson;
import com.mongodb.WriteResult;
import edu.sjsu.cmpe282.hw1.Application;
import edu.sjsu.cmpe282.hw1.dao.EmployeeDAO;
import edu.sjsu.cmpe282.hw1.domain.Employee;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static spark.Spark.halt;


/**
 * Created by Liping on 2/26/16.
 */
public class EmployeeResource {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO(Application.datastore);
    private static final Gson gson = new Gson();

    public static Employee get(Request req, Response res) {
        Employee employee = employeeDAO.get(Integer.parseInt(req.params(":id")));
        if (employee == null) {
            halt(HttpServletResponse.SC_NOT_FOUND);
        }
        res.status(HttpServletResponse.SC_OK);
        res.type("application/json");
        return employee;
    }

    public static String create(Request req, Response res) {
        Employee employee = gson.fromJson(req.body(), Employee.class);
        if (employeeDAO.exists("id", employee.getId())) {
            halt(HttpServletResponse.SC_CONFLICT);
        } else {
            res.status(HttpServletResponse.SC_CREATED);
            employeeDAO.save(employee);
        }
        return "";
    }

    public static List<Employee> getAll(Request req, Response res) {
        List<Employee> employees = employeeDAO.getAll();
        if (employees.size() == 0) {
            res.status(HttpServletResponse.SC_NOT_FOUND);
        } else {
            res.status(HttpServletResponse.SC_OK);
        }
        res.type("application/json");
        return employees;
    }

    public static String update(Request req, Response res) {
        Employee employee = gson.fromJson(req.body(), Employee.class);
        employee.setId(Integer.parseInt(req.params(":id")));
        try {
            employeeDAO.update(employee);
        } catch (Exception e) {
            halt(HttpServletResponse.SC_NOT_FOUND);
        }
        res.status(HttpServletResponse.SC_OK);
        return "";
    }

    public static String delete(Request req, Response res) {
        WriteResult writeResult = employeeDAO.deleteById(Integer.parseInt(req.params(":id")));
        if (writeResult.getN() == 0) {
            halt(HttpServletResponse.SC_NOT_FOUND);
        }
        res.status(HttpServletResponse.SC_OK);
        return "";
    }
}
