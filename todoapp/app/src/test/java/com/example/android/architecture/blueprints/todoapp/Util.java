package com.example.android.architecture.blueprints.todoapp;

import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/18/2017.
 */

public class Util {

    public static List<Poc> loadRemotePocs(Class object) {
        ArrayList<Poc> pocs = new ArrayList<>();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        try {
            InputStream resource = object.getClassLoader().getResourceAsStream("pocs_load_success.json");
            Reader reader = new InputStreamReader(resource, "UTF-8");
            JsonElement jsonElement = jsonParser.parse(reader);
            //Create generic type
            Type type = new TypeToken<List<Poc>>() {
            }.getType();
            return gson.fromJson(jsonElement, type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pocs;
    }
}
