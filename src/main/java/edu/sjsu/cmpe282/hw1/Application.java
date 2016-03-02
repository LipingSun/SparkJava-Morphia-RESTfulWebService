package edu.sjsu.cmpe282.hw1;

import edu.sjsu.cmpe282.hw1.rest.EmployeeResource;
import edu.sjsu.cmpe282.hw1.rest.ProjectResource;
import edu.sjsu.cmpe282.hw1.utils.JsonTransformer;

import static spark.Spark.*;

/**
 * Created by Liping on 2/26/16.
 */
class Application {
    private static final String url = "/cmpe282LipingSun391/rest";
    public static void main(String[] args) {
        get(url + "/employee/:id", EmployeeResource::get, new JsonTransformer());
        get(url + "/employee", EmployeeResource::getAll, new JsonTransformer());
        post(url + "/employee", EmployeeResource::create);
        put(url + "/employee/:id", EmployeeResource::update);
        delete(url + "/employee/:id", EmployeeResource::delete);

        get(url + "/project/:id", ProjectResource::get, new JsonTransformer());
        get(url + "/project", ProjectResource::getAll, new JsonTransformer());
        post(url + "/project", ProjectResource::create);
        put(url + "/project/:id", ProjectResource::update);
        delete(url + "/project/:id", ProjectResource::delete);
    }
}