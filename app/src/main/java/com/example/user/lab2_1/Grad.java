package com.example.user.lab2_1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 11/21/16.
 */

public class Grad implements java.io.Serializable {
    /*
        "lng":21.431407,
        "geonameId":785842,
        "countrycode":"MK",
        "name":"Skopje",
        "fclName":"city, village,...",
        "toponymName":"Skopje",
        "fcodeName":"capital of a political entity",
        "wikipedia":"en.wikipedia.org/wiki/Skopje",
        "lat":41.996457,
        "fcl":"P",
        "population":474889,
        "fcode":"PPLC"
    */

    public JSONObject json;

    public Grad(JSONObject obj) throws JSONException {
        lng = obj.getDouble("lng");
        lat = obj.getDouble("lat");
        geoNameId = obj.getInt("geonameId");
        countryCode = obj.getString("countrycode");
        name = obj.getString("name");
        toponymName = obj.getString("toponymName");
        fcodeName = obj.getString("fcodeName");
        fclName = obj.getString("fclName");
        fcode = obj.getString("fcode");
        fcl = obj.getString("fcl");
        population = obj.getInt("population");
        wikipedia = obj.getString("wikipedia");
        json = obj;
    }

    double lng;
    int geoNameId;
    String countryCode;
    String name;
    String fclName;
    String toponymName;
    String fcodeName;
    String wikipedia;
    double lat;
    String fcl;
    int population;
    String fcode;
}
