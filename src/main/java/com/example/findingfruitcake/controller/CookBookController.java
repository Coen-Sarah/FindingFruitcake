package com.example.findingfruitcake.controller;

import com.almasb.fxgl.ui.UIController;
import com.example.findingfruitcake.model.FoodItem;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.example.findingfruitcake.model.CookBook.getCookBook;

public class CookBookController implements UIController {

    @FXML
    VBox root = new VBox();
    @FXML
    Label inventory = new Label();
    @FXML
    Label cookBook = new Label();
    @FXML
    HBox boxContainer = new HBox();
    @FXML
    GridPane container = new GridPane();
    @FXML
    ScrollPane scroll = new ScrollPane();
    @FXML
    VBox details = new VBox();

    Label detailName = new Label();
    ImageView detailSprite = new ImageView();
    Label detailType = new Label();
    Label detailDescription = new Label();

    @Override
    public void init() {

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(24);
            column.setMaxWidth(24);
            container.getColumnConstraints().add(column);
        }
        RowConstraints row = new RowConstraints();
        row.setFillHeight(false);
        row.setPrefHeight(24);
        row.setMaxHeight(24);
        container.getRowConstraints().add(row);

        inventory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                UIViewer.showCookbook();
            }
        });

        container.setGridLinesVisible(true);

        cookBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                UIViewer.showCookbook();
            }
        });

        details.getChildren().addAll(detailName, detailSprite, detailType, detailDescription);
    }

    public void update() {
        int columnCount = 0;
        int rowCount = 0;

        for (FoodItem food : getCookBook().getAllFoods()) {
            StackPane stackPane = new StackPane();
            ImageView background = new ImageView(getAssetLoader().loadImage("node.png"));
            ImageView sprite = new ImageView();
            if(food.foundProperty().getValue()) {
                sprite.setImage(food.getSprite());
                sprite.setEffect(null);
            } else {
                sprite.setImage(food.getSprite());
                sprite.setEffect(food.getSpriteOutline().getEffect());
            }

            sprite.setPreserveRatio(true);
            sprite.setFitWidth(16);
            stackPane.getChildren().add(background);
            stackPane.getChildren().add(sprite);

            container.add(stackPane, columnCount, rowCount);

            stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    StringBuilder foodTypeBuilder = new StringBuilder();

                    if(food.foundProperty().getValue()) {
                        detailName.setText(food.getName());
                        detailSprite.setImage(food.getSprite());
                        detailSprite.setEffect(null);
                        food.getFoodType().forEach(new Consumer<FoodItem.FoodType>() {
                            @Override
                            public void accept(FoodItem.FoodType food) {
                                foodTypeBuilder.append(food.name() + "\n");
                            }
                        });
                        detailType.setText(foodTypeBuilder.toString());
                        detailDescription.setText(food.getDescription());
                    } else {
                        detailName.setText("???");
                        detailSprite.setImage(food.getSprite());
                        detailSprite.setEffect(food.getSpriteOutline().getEffect());
                        detailType.setText("???");
                        detailDescription.setText("???");
                    }


                    scroll.setMaxWidth(getAppWidth()*.75);
                    details.setMinWidth(getAppWidth()*.25);
                }
            });

            if (columnCount == 4) {
                rowCount++;
                columnCount = 0;
                RowConstraints row = new RowConstraints();
                row.setFillHeight(false);
                row.setPrefHeight(24);
                row.setMaxHeight(24);
                container.getRowConstraints().add(row);

            } else {
                columnCount++;
            }
        }
    }
}
