package com.example.findingfruitcake.scenes;

import com.almasb.fxgl.scene.SubScene;
import com.example.findingfruitcake.model.FoodItem;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.example.findingfruitcake.model.CookBook.getCookBook;

public class CookBookSubScene extends SubScene {

    GridPane root;
    Label name = new Label();
    ImageView detailSprite = new ImageView();
    Label foodType = new Label();
    Label description = new Label();

    public CookBookSubScene(){

        double width = getAppWidth()*.75;

        root = new GridPane();
        root.setPrefWidth(width);
        root.setMinWidth(width);
        root.setMinWidth(width);

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

        getCookBook().getAllFoods().addListener(new ListChangeListener<FoodItem>() {

            @Override
            public void onChanged(Change<? extends FoodItem> change) {
                while (change.next()) {
                    for(int i = 0; i < change.getList().size(); i++){
                        System.out.println(change.getList().get(i).foundProperty().getValue());
                        if(change.getList().get(i).foundProperty().getValue()){
                            root.getChildren().set(i, change.getList().get(i).getSpriteView());
                        } else {
                            root.getChildren().set(i, change.getList().get(i).getSpriteOutline());
                        }
                    }
                }
            }
        });

        for(FoodItem food : getCookBook().getAllFoods()){

            ImageView sprite;

            if(food.foundProperty().getValue()){
                sprite = food.getSpriteView();
            } else {
                sprite = food.getSpriteOutline();
            }

            root.add(sprite,columnCount,rowCount);
            sprite.setOnMouseClicked(new EventHandler<MouseEvent>() {

                int rows = root.getRowCount();

                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(root.getChildren());
                    if(root.getChildren().contains(name)){
                        root.getChildren().remove(name);
                        root.getChildren().remove(detailSprite);
                        root.getChildren().remove(foodType);
                        root.getChildren().remove(description);
                    }

                    detailSprite = new ImageView();
                    detailSprite.setImage(food.getSprite());

                    if(food.foundProperty().getValue()){
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

                    } else {
                        String unknownFood = "???";
                        name.setText(unknownFood);
                        detailSprite.setEffect(food.getOutlineEffect());
                        foodType.setText(unknownFood);
                        description.setText(unknownFood);

                    }

                    root.add(name, 0, rows + 2);
                    root.add(detailSprite, 0,rows + 3);
                    root.add(foodType, 1,rows + 3, 2, 1);
                    root.add(description, 2,rows + 3, 2, 1);

                }
            });

            if(columnCount == 4){
                rowCount++ ;
                columnCount = 0;
            } else {
                columnCount++;
            }
        }

        this.getContentRoot().getChildren().add(root);

        // ?sortable??
    }

    public static void update(){

    }

}
