package com.example.findingfruitcake.util;

import com.example.findingfruitcake.model.FoodItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class FoodCell<T> extends ListCell<T> {
    VBox vbox = new VBox();
    ImageView sprite = new ImageView();
    Label name = new Label();

    public FoodCell() {
        super();
        vbox.getChildren().addAll(sprite, name);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setGraphic(null);
        } else {
            name.setText(((FoodItem) item).getName());
            sprite.setImage(((FoodItem) item).getSprite());
            setGraphic(vbox);
        }
    }
}
