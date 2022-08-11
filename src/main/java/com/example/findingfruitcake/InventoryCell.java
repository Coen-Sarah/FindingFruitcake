package com.example.findingfruitcake;

import com.example.findingfruitcake.model.FoodItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class InventoryCell<T> extends ListCell<T> {
    HBox hbox = new HBox();
    ImageView sprite = new ImageView();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    Button button = new Button("(>)");
    String lastItem;

    public InventoryCell() {
        super();
        hbox.getChildren().addAll(sprite, label, pane);
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setGraphic(null);
        } else {
            label.setText(item.toString());
            sprite.setImage(getAssetLoader().loadImage(("food/") + ((FoodItem)item).getName() + ".png"));
            setGraphic(hbox);
        }
    }

}
