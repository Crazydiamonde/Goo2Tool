package com.crazine.goo2tool.gui;

import com.crazine.goo2tool.functional.Save;
import com.crazine.goo2tool.properties.PropertiesLoader;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FX_Scene {

    private static Scene scene;
    public static Scene getScene() {
        return scene;
    }


    public static void buildScene(Stage stage) {

        TabPane tabPane = new TabPane();
        tabPane.prefHeightProperty().bind(stage.heightProperty());

        Tab profileTab = new Tab("Profile");
        profileTab.setContent(FX_Profile.getProfileView());
        tabPane.getTabs().add(profileTab);

        Tab modsTab = new Tab("Mods");
        modsTab.setContent(FX_Mods.getModView());
        tabPane.getTabs().add(modsTab);

        Tab optionsTab = new Tab("Options");
        optionsTab.setContent(FX_Options.getOptionsView());
        tabPane.getTabs().add(optionsTab);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> Save.save(stage));
        Button saveAndPlayButton = new Button("Save and Launch World of Goo 2!");
        saveAndPlayButton.setOnAction(event -> {
            BooleanProperty finished = Save.save(stage);
            finished.addListener((observable, oldValue, newValue) -> {
                try {
                    File customWOG2Dir = new File(PropertiesLoader.getProperties().getCustomWorldOfGoo2Directory());
                    File[] children = customWOG2Dir.listFiles();
                    if (children == null) return;
                    for (File child : children) {
                        if (child.isDirectory()) continue;
                        if (Files.isExecutable(child.toPath())) {
                            ProcessBuilder processBuilder = new ProcessBuilder(child.getPath());
                            processBuilder.start();
                        }
                    }
                } catch (IOException e) {
                    FX_Alarm.error(e);
                }
            });
        });
        HBox hBox = new HBox(saveButton, saveAndPlayButton);
        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        VBox vBox = new VBox(FX_Menu.getMenuBar(), tabPane, hBox);

        scene = new Scene(vBox);

    }
}
