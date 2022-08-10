module com.example.findingfruitcake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.gameplay;
    requires com.almasb.fxgl.io;
    requires com.almasb.fxgl.entity;
    requires com.almasb.fxgl.core;
    requires com.almasb.fxgl.all;
    requires com.almasb.fxgl.scene;

    opens com.example.findingfruitcake to javafx.fxml;

    opens assets.textures.food to com.almasb.fxgl.all;
    opens assets.textures.player to com.almasb.fxgl.all;
    opens assets.levels to com.almasb.fxgl.all;
    
    exports com.example.findingfruitcake;

}