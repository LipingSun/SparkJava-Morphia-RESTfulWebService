package edu.sjsu.cmpe282.hw1;

import edu.sjsu.cmpe282.hw1.rest.EmployeeResource;
import edu.sjsu.cmpe282.hw1.rest.ProjectResource;
import edu.sjsu.cmpe282.hw1.utils.JsonTransformer;

import static spark.Spark.*;

/**
 * Created by Liping on 2/26/16.
 */
public class Application {
    public static void main(String[] args) {
        get("/employee/:id", EmployeeResource::get, new JsonTransformer());
        get("/employee", EmployeeResource::getAll, new JsonTransformer());
        post("/employee", EmployeeResource::create);
        put("/employee/:id", EmployeeResource::update);
        delete("/employee/:id", EmployeeResource::delete);

        get("/project/:id", ProjectResource::get, new JsonTransformer());
        get("/project", ProjectResource::getAll, new JsonTransformer());
        post("/project", ProjectResource::create);
        put("/project/:id", ProjectResource::update);
        delete("/project/:id", ProjectResource::delete);
    }
}
