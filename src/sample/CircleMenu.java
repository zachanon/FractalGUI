package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class CircleMenu extends GenerativeMenu{

    private Stage stage;
    private final String title = "Circle Fractals";
    private int HEIGHT=600, WIDTH=400;

    private ArrayList<TextOptionsField> optionsFields = new ArrayList<>(){
        {
            add(new TextOptionsField("Iterations","Number of iterations (min 0, max 10)"));
            add(new TextOptionsField("Scale","Scale of Seed Circle (int: min 1, max 10"));
        }
    };


    public CircleMenu(){
        this.stage = new Stage();
        stage.setTitle(title);
    }
    public void drawMenu(){
        GridPane menu = new GridPane();

        for (int i = 0; i < optionsFields.size(); i++)
            menu.addRow(i, optionsFields.get(i).getSelf().);

        stage.setScene(new Scene(menu, WIDTH, HEIGHT));
        stage.show();
    }
    public String getTitle(){
        return title;
    }

    private void drawFractal(){
        Group root = new Group();
        int scale = 1;
        int iters = 1;

        for(var field : optionsFields){
            if(field.getName() == "Scale")
                scale = Integer.parseInt(field.getFieldValue());
            if(field.getName() == "Iters")
                iters = Integer.parseInt(field.getFieldValue());
        }

        var radius = scale*8;
        int x = WIDTH/2;
        int y = HEIGHT/2;

        Circle seed = new Circle(x,y,radius);
        seed.setStroke(Color.BLACK);
        seed.setFill(Color.TRANSPARENT);

        root.getChildren().add(seed);
        stage.setScene(new Scene(root, HEIGHT, WIDTH));
        stage.show();
    }
}
