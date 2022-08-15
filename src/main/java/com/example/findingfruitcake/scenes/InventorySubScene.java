package com.example.findingfruitcake.scenes;

import com.almasb.fxgl.scene.SubScene;
import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.PlayerBag;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class InventorySubScene extends SubScene {

    GridPane root = new GridPane();

    Label name = new Label();
    ImageView detailSprite = new ImageView();
    Label foodType = new Label();
    Label description = new Label();

    public InventorySubScene(){

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            root.getColumnConstraints().add(column);
        }

        Image inventoryImage = getAssetLoader().loadImage("inventory.png");

        this.getContentRoot().setBackground(new Background(new BackgroundImage(inventoryImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        int columnCount = 0;
        int rowCount = 0;

        this.getContentRoot().getChildren().add(root);

        for(FoodItem food : PlayerBag.getInventory()){
            ImageView sprite = new ImageView(food.getSprite());
            sprite.setPreserveRatio(true);
            sprite.setFitWidth(16);
            root.add(sprite,columnCount,rowCount);

            sprite.setOnMouseClicked(new EventHandler<MouseEvent>() {
                int rows = root.getRowCount();
                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(root.getChildren().contains(name)){
                        root.getChildren().remove(name);
                        root.getChildren().remove(detailSprite);
                        root.getChildren().remove(foodType);
                        root.getChildren().remove(description);
                    }

                    StringBuilder foodTypeBuilder = new StringBuilder();
                    name.setText(food.getName());
                    food.getFoodType().forEach(new Consumer<FoodItem.FoodType>() {
                        @Override
                        public void accept(FoodItem.FoodType food) {
                            foodTypeBuilder.append(food.name() + "\n");
                        }
                    });
                    foodType.setText(foodTypeBuilder.toString());
                    description.setText(food.getDescription());
                    System.out.println(food.getName());

                    root.add(name, 0, rows + 2);
                    root.add(detailSprite, 0,rows + 3);
                    root.add(foodType, 1,rows + 3, 2, 1);
                    root.add(description, 2,rows + 3, 2, 1);
                }
            });

            if(columnCount == 4){
                rowCount++;
                columnCount = 0;
            } else {
                columnCount++;
            }
        }
        // ?sortable??
    }
}
