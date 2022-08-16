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
    requires com.google.gson;

    opens com.example.findingfruitcake to javafx.fxml;
    opens com.example.findingfruitcake.model to com.google.gson;

    opens assets.textures to com.almasb.fxgl.all;
    opens assets.textures.player to com.almasb.fxgl.all;
    opens assets.textures.food to com.almasb.fxgl.all;
    opens assets.levels to com.almasb.fxgl.all;
    opens assets.json to com.almasb.fxgl.all;
    opens assets.ui to com.almasb.fxgl.all;


    exports com.example.findingfruitcake;
    exports com.example.findingfruitcake.controller;
    opens com.example.findingfruitcake.controller to javafx.fxml;
    exports com.example.findingfruitcake.util;
    opens com.example.findingfruitcake.util to javafx.fxml;

}