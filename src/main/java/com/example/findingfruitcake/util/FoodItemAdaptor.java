package com.example.findingfruitcake.util;

import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.FoodItem.FoodType;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class FoodItemAdaptor extends TypeAdapter<FoodItem> {
    
    @Override
    public FoodItem read(JsonReader reader) throws IOException {
        FoodItem foodItem = new FoodItem();
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
                foodItem.setName(reader.nextString());
            }

            if("description".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                foodItem.setDescription(reader.nextString());
            }

            if("foodType".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                ArrayList<FoodType> foodTypes = new ArrayList<>();
                reader.beginArray();
                while(reader.hasNext()){
                    System.out.println(foodItem.getName());
                    FoodType foodType = FoodType.valueOf(reader.nextString());
                    foodTypes.add(foodType);
                }
                reader.endArray();
                foodItem.setFoodType(foodTypes);
            }
        }
        reader.endObject();
        return foodItem;
    }

    @Override
    public void write(JsonWriter writer, FoodItem foodItem) throws IOException {
        writer.beginObject();

        writer.endObject();
    }

}
