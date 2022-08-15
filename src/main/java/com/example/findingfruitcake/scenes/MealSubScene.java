package com.example.findingfruitcake.scenes;

import com.almasb.fxgl.scene.SubScene;
import com.example.findingfruitcake.FoodCell;
import com.example.findingfruitcake.UIViewer;
import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.Meal;
import com.example.findingfruitcake.model.PlayerBag;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.example.findingfruitcake.model.Meal.getMeal;

public class MealSubScene extends SubScene {

    GridPane root = new GridPane();

    Label drink = new Label("Drink");
    Label main = new Label("Main");
    Label side = new Label("Side");
    Label dessert = new Label("Dessert");
    Label inventory = new Label("Inventory");

    ImageView drinkSprite = new ImageView();
    ImageView mainSprite = new ImageView();
    ImageView sideSprite = new ImageView();
    ImageView dessertSprite = new ImageView();

    ListView<FoodItem> drinkInventory = new ListView<FoodItem>();
    ListView<FoodItem> mainInventory = new ListView<FoodItem>();
    ListView<FoodItem> sideInventory = new ListView<FoodItem>();
    ListView<FoodItem> dessertInventory = new ListView<FoodItem>();

    Button makeDinner = new Button("Make Dinner");
    Button keepLooking = new Button("Keep Looking!");

    public MealSubScene() {

        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            column.setMaxWidth(getAppWidth() * .20);
            root.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(5);
            if (i == 5) {
                row.setPercentHeight(20);
                row.setMaxHeight(getAppHeight() * .20);
            }
            root.getRowConstraints().add(row);
        }

        Image inventoryImage = getAssetLoader().loadImage("inventory.png");

        this.getContentRoot().setBackground(new Background(new BackgroundImage(inventoryImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        addGridElements();
        addListeners();
        setSprites();

        this.getContentRoot().getChildren().add(root);
    }

    private void addGridElements() {
        root.add(drink, 0,0);
        root.add(main, 1,0);
        root.add(side, 2,0);
        root.add(dessert, 3,0);

        root.add(drinkSprite, 0, 1);
        root.add(mainSprite, 1, 1);
        root.add(sideSprite, 2, 1);
        root.add(dessertSprite, 3, 1);

        root.add(keepLooking, 0,2,2,1);
        root.add(makeDinner, 2,2,2,1);
        makeDinner.setVisible(false);

        root.add(inventory, 0,4,1,4);

        drinkInventory.setCellFactory( cell -> new FoodCell<>());
        mainInventory.setCellFactory( cell -> new FoodCell<>());
        sideInventory.setCellFactory( cell -> new FoodCell<>());
        dessertInventory.setCellFactory( cell -> new FoodCell<>());

        drinkInventory.setItems(PlayerBag.getDrinks());
        mainInventory.setItems(PlayerBag.getMains());
        sideInventory.setItems(PlayerBag.getSides());
        dessertInventory.setItems(PlayerBag.getDesserts());

        root.add(drinkInventory, 0,5);
        root.add(mainInventory, 1,5);
        root.add(sideInventory, 2,5);
        root.add(dessertInventory, 3,5);

    }

    private void addListeners() {
        drinkInventory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FoodItem selected = drinkInventory.getSelectionModel().getSelectedItem();
                if(getMeal().getDrink() != null) {
                    PlayerBag.addFoodToInventory(getMeal().getDrink());
                }
                getMeal().setDrink(selected);
                PlayerBag.removeFoodFromInventory(selected);
                setSprites();
            }
        });
        mainInventory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FoodItem selected = mainInventory.getSelectionModel().getSelectedItem();
                if(getMeal().getMain() != null) {
                    PlayerBag.addFoodToInventory(getMeal().getMain());
                }
                getMeal().setMain(selected);
                PlayerBag.removeFoodFromInventory(selected);
                setSprites();
            }
        });
        sideInventory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FoodItem selected = sideInventory.getSelectionModel().getSelectedItem();
                if(getMeal().getSide() != null) {
                    PlayerBag.addFoodToInventory(getMeal().getSide());
                }
                getMeal().setSide(selected);
                PlayerBag.removeFoodFromInventory(selected);
                setSprites();
            }
        });
        dessertInventory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FoodItem selected = dessertInventory.getSelectionModel().getSelectedItem();
                if(getMeal().getDessert() != null) {
                    PlayerBag.addFoodToInventory(getMeal().getDessert());
                }
                getMeal().setDessert(selected);
                PlayerBag.removeFoodFromInventory(selected);
                setSprites();
            }
        });
        keepLooking.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UIViewer.showGameUI();
            }
        });
        makeDinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getMeal().judgeMeal();
                UIViewer.showGameUI();
            }
        });
    }

    private void setSprites(){
        if( getMeal().getDrink() != null) {
            drinkSprite.setImage(getMeal().getDrink().getSprite());
        } else {
            drinkSprite.setImage(Meal.getSprite());
        }
        if(getMeal().getMain() != null) {
            mainSprite.setImage(getMeal().getMain().getSprite());
        } else {
            mainSprite.setImage(Meal.getSprite());
        }
        if( getMeal().getSide() != null) {
            sideSprite.setImage(getMeal().getSide().getSprite());
        } else {
            sideSprite.setImage(Meal.getSprite());
        }
        if( getMeal().getDessert() != null) {
            dessertSprite.setImage(getMeal().getDessert().getSprite());
        } else {
            dessertSprite.setImage(Meal.getSprite());
        }

        getMeal().checkIfComplete();
        if(getMeal().isCompletedMeal()){
            makeDinner.setVisible(true);
        }
    }
}


