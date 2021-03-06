package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

  public static final String JSON_NAME_STRING_KEY = "name";
  public static final String JSON_MAIN_NAME_STRING_KEY = "mainName";
  public static final String JSON_ALSO_KNOWN_AS_STRING_KEY = "alsoKnownAs";
  public static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
  public static final String JSON_DESCRIPTION_KEY = "description";
  public static final String JSON_IMAGE_KEY = "image";
  public static final String JSON_INGREDIENTS_KEY = "ingredients";

  public static Sandwich parseSandwichJson(String json) {
    try {
      JSONObject jsonObject = new JSONObject(json);

      // name is an array.
      JSONObject name = jsonObject.getJSONObject(JSON_NAME_STRING_KEY);

      // get String from name
      String mainName = name.getString(JSON_MAIN_NAME_STRING_KEY);

      // get alsoKnownAs Array
      JSONArray alsoKnownAsList = name.getJSONArray(JSON_ALSO_KNOWN_AS_STRING_KEY);
      ArrayList<String> alsoKnownAs = new ArrayList<>();

      // for Loop for each item inside and place inside alsoKnownAs ArrayList
      for (int i = 0; i < alsoKnownAsList.length(); i++) {
        alsoKnownAs.add(alsoKnownAsList.getString(i));
      }

      // Get String for placeOfOrigin
      String placeOfOrigin = jsonObject.optString(JSON_PLACE_OF_ORIGIN_KEY);
      // Get String for description
      String description = jsonObject.optString(JSON_DESCRIPTION_KEY);
      // Get String for image
      String image = jsonObject.optString(JSON_IMAGE_KEY);

      // get ingredient Array
      JSONArray ingredientsList = jsonObject.getJSONArray(JSON_INGREDIENTS_KEY);
      ArrayList<String> ingredients = new ArrayList<>();

      // for Loop for each item inside and place inside ingredients ArrayList
      for (int i = 0; i < ingredientsList.length(); i++) {
        ingredients.add(ingredientsList.getString(i));
      }

      // return new Sandwich
      return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

  }
}
