package edu.sjsu.cmpe282.hw1;

import com.mongodb.MongoClient;
import edu.sjsu.cmpe282.hw1.rest.EmployeeResource;
import edu.sjsu.cmpe282.hw1.rest.ProjectResource;
import edu.sjsu.cmpe282.hw1.utils.JsonTransformer;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.IOException;
import java.util.Properties;

import static spark.Spark.*;

/**
 * Created by Liping on 2/26/16.
 */
public class Application {


    private static final Morphia morphia = new Morphia();
    private static MongoClient mongoClient;
    public static Datastore datastore;

    private static final String url = "/cmpe282LipingSun391/rest";

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.load(Application.class.getResourceAsStream("/mongodb.properties"));

        mongoClient = new MongoClient(prop.getProperty("host"), Integer.parseInt(prop.getProperty("port")));
        datastore = morphia.createDatastore(mongoClient, prop.getProperty("database"));

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