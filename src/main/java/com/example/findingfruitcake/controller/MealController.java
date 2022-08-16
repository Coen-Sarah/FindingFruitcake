package com.example.findingfruitcake.controller;

import com.almasb.fxgl.ui.UIController;
import com.example.findingfruitcake.FoodCell;
import com.example.findingfruitcake.UIViewer;
import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.Meal;
import com.example.findingfruitcake.model.PlayerBag;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.example.findingfruitcake.model.Meal.getMeal;
import static com.example.findingfruitcake.model.Meal.getSprite;

public class MealController implements UIController {

    @FXML
    GridPane root = new GridPane();

    @FXML
    StackPane drinkPane = new StackPane();
    @FXML
    StackPane mainPane = new StackPane();
    @FXML
    StackPane sidePane = new StackPane();
    @FXML
    StackPane dessertPane = new StackPane();

    @FXML
    ImageView drinkSprite = new ImageView();
    @FXML
    ImageView mainSprite = new ImageView();
    @FXML
    ImageView sideSprite = new ImageView();
    @FXML
    ImageView dessertSprite = new ImageView();

    @FXML
    Button keepLooking = new Button();
    @FXML
    Button makeDinner = new Button();

    @FXML
    ListView<FoodItem> drinkInventory = new ListView<>();
    @FXML
    ListView<FoodItem> mainInventory = new ListView<>();
    @FXML
    ListView<FoodItem> sideInventory = new ListView<>();
    @FXML
    ListView<FoodItem> dessertInventory = new ListView<>();

    ImageView node = new ImageView(getAssetLoader().loadImage("node.png"));
    ImageView plate = new ImageView(getSprite());

    @Override
    public void init() {

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            root.getColumnConstraints().add(column);
        }

        node.setPreserveRatio(true);
        node.setFitWidth(64);

        drinkPane.getChildren().add(0, createImageBackground());
        drinkPane.getChildren().add(1, createPlateNode());
        mainPane.getChildren().add(0, createImageBackground());
        mainPane.getChildren().add(1, createPlateNode());
        sidePane.getChildren().add(0, createImageBackground());
        sidePane.getChildren().add(1, createPlateNode());
        dessertPane.getChildren().add(0, createImageBackground());
        dessertPane.getChildren().add(1, createPlateNode());

        makeDinner.setVisible(false);

        drinkInventory.setCellFactory( cell -> new FoodCell<>());
        mainInventory.setCellFactory( cell -> new FoodCell<>());
        sideInventory.setCellFactory( cell -> new FoodCell<>());
        dessertInventory.setCellFactory( cell -> new FoodCell<>());

        drinkInventory.setItems(PlayerBag.getDrinks());
        mainInventory.setItems(PlayerBag.getMains());
        sideInventory.setItems(PlayerBag.getSides());
        dessertInventory.setItems(PlayerBag.getDesserts());

        addListeners();
        setSprites();

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

    private ImageView createImageBackground(){
        ImageView node = new ImageView(getAssetLoader().loadImage("node.png"));
        node.setPreserveRatio(true);
        node.setFitWidth(64);
        return node;
    }

    private ImageView createPlateNode(){
        ImageView node = new ImageView(getSprite());
        node.setPreserveRatio(true);
        node.setFitWidth(64);
        return node;
    }
}
