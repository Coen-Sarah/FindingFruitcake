package com.example.findingfruitcake.util;

import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.FoodItem.FoodType;
import com.example.findingfruitcake.model.Recipe;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.findingfruitcake.model.CookBook.getCookBook;

public class RecipeAdaptor extends TypeAdapter<Recipe> {
    
    @Override
    public Recipe read(JsonReader reader) throws IOException {
        Recipe recipe = new Recipe();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }

            if ("name".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                recipe.setName(reader.nextString());
            }

            if("output".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                FoodItem output = getCookBook().getFoodByName(reader.nextString());
                recipe.setOutput(output);
            }

            if("ingredients".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                ArrayList<FoodItem> ingredients = new ArrayList<>();
                reader.beginArray();
                System.out.println("HERE");
                while(reader.hasNext()){
                    //System.out.println(reader.peek());
                    FoodItem ingredient = getCookBook().getFoodByName(reader.nextString());
                    ingredients.add(ingredient);
                }
                reader.endArray();
                recipe.setIngredients(ingredients);
            }
        }
        reader.endObject();
        System.out.println("food added");
        return recipe;
    }

    @Override
    public void write(JsonWriter writer, Recipe foodItem) throws IOException {
        writer.beginObject();

        writer.endObject();
    }

}
