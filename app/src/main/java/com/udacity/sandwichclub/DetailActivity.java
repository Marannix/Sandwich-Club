package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView description;
    private TextView descriptionLabel;
    private TextView placeOfOrigin;
    private TextView placeOfOriginLabel;
    private TextView ingredient;
    private TextView ingredientLabel;
    private TextView alsoKnownAs;
    private TextView alsoKnownAsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        description = findViewById(R.id.description_tv);
        placeOfOrigin = findViewById(R.id.origin_tv);
        ingredient = findViewById(R.id.ingredients_tv);
        alsoKnownAs = findViewById(R.id.also_known_tv);

        descriptionLabel = findViewById(R.id.description_label);
        placeOfOriginLabel = findViewById(R.id.place_of_origin_label);
        ingredientLabel = findViewById(R.id.ingredients_label);
        alsoKnownAsLabel = findViewById(R.id.also_known_label);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (!sandwich.getDescription().isEmpty()) {
            description.setText(sandwich.getDescription());
        } else {
            description.setVisibility(View.GONE);
            descriptionLabel.setVisibility(View.GONE);
        }

        if (!sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        } else {
            placeOfOrigin.setVisibility(View.GONE);
            placeOfOriginLabel.setVisibility(View.GONE);
        }

        if (!sandwich.getIngredients().isEmpty()) {
            ingredient.setText(sandwich.getIngredients().toString());
        } else {
            ingredient.setVisibility(View.GONE);
            ingredientLabel.setVisibility(View.GONE);
        }

        if (!sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnownAs.setText(sandwich.getAlsoKnownAs().toString());
        } else {
            alsoKnownAs.setVisibility(View.GONE);
            alsoKnownAsLabel.setVisibility(View.GONE);
        }

    }
}
