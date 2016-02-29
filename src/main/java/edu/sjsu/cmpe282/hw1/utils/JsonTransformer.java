package edu.sjsu.cmpe282.hw1.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Liping on 2/27/16.
 */
public class JsonTransformer implements ResponseTransformer {
    private final Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
