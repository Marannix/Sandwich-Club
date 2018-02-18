package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            // name is an array.
            JSONObject name = jsonObject.getJSONObject("name");

            // get String from name
            String mainName = name.getString("mainName");

            // get alsoKnownAs Array
            JSONArray alsoKnownAsList = name.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAs = new ArrayList<>();

            // for Loop for each item inside and place inside alsoKnownAs ArrayList
            for (int i = 0; i < alsoKnownAsList.length(); i++) {
                alsoKnownAs.add(alsoKnownAsList.getString(i));
            }

            // Get String for placeOfOrigin
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            // Get String for description
            String description = jsonObject.getString("description");
            // Get String for image
            String image = jsonObject.getString("image");

            // get ingredient Array
            JSONArray ingredientsList = jsonObject.getJSONArray("ingredients");
            ArrayList<String> ingredients = new ArrayList<>();

            // for Loop for each item inside and place inside ingredients ArrayList
            for (int i = 0; i < ingredientsList.length(); i++) {
                ingredients.add(ingredientsList.getString(i));
            }

            // return new Sandwich
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
