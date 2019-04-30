package com.cssweb.ops.nlp;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Config {


    private String appId;
    private String apiKey;
    private String authId;
    private String scene;

    private Map<String, Map<String, Object>> properties = null;

    public boolean load() {


        try {
            byte[] content = Files.readAllBytes(Paths.get("nlp.yaml"));
            if (content == null)
                return false;

            String str = new String(content);

            Yaml yaml = new Yaml();
            properties = yaml.loadAs(str, HashMap.class);


            appId = (String) getValue("app", "appid");
            apiKey = (String) getValue("app", "api_key");
            authId = (String) getValue("app", "auth_id");
            scene = (String) getValue("app", "scene");

            return true;

        } catch (IOException e) {
            e.printStackTrace();

        }

        return false;
    }

    private Object getValue(String root, String key) {
        Map<String, Object> rootProperty = properties.get(root);
        return rootProperty.getOrDefault(key, "");
    }

    public String getAppId() {
        return appId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getAuthId() {
        return authId;
    }

    public String getScene() {
        return scene;
    }
}
