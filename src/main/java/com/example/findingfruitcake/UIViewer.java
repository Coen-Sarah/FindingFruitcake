package com.example.findingfruitcake;

import com.almasb.fxgl.inventory.view.InventoryView;
import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.PlayerBag;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class UIViewer {

    public static String INVENTORY = "INVENTORY";
    public static String DEFAULT = "DEFAULT";

    private static Image inventoryImage = getAssetLoader().loadImage("inventory.png");
    private static ImageView inventory = new ImageView(inventoryImage);
    private static InventoryView<FoodItem> inventoryView;

    public static void showInventory() {

        inventoryView = new InventoryView<>(PlayerBag.getInventory());
        inventoryView.getListView().setCellFactory(new Callback<ListView<FoodItem>, ListCell<FoodItem>>() {
            @Override
            public ListCell<FoodItem> call(ListView<FoodItem> foodItemListView) {
                return new InventoryCell();
            }
        });

        getGameScene().addUINode(inventoryView);

    }

    public static void hideInventory() {
        getGameScene().removeUINode(inventoryView);

    }

    public static String getActiveUI(){
        if(getGameScene().getUINodes().contains(inventoryView)){
            return INVENTORY;
        } else {
            return DEFAULT;
        }
    }

}
