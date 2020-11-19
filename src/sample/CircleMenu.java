package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class CircleMenu extends GenerativeMenu{

    private Stage stage;
    private final String title = "Circle Fractals";
    private int HEIGHT=600, WIDTH=600;


    public CircleMenu(){
        this.stage = new Stage();
        stage.setTitle(title);
    }
    public void drawMenu(){
        GridPane menu = new GridPane();

        Label scaleLabel = new Label("Scale of the initial circle (5-15 recommended):");
        TextField scaleField = new TextField();

        Label itersLabel = new Label("Number of iterations to draw (under 15 recommended):");
        TextField itersField = new TextField();

        Button drawButton = new Button("Draw");
        drawButton.setOnAction(actionEvent -> {
            drawFractal(Integer.parseInt(scaleField.getText()),
                    Integer.parseInt(itersField.getText()));
        });

        menu.addRow(0, scaleLabel, scaleField);
        menu.addRow(1, itersLabel, itersField);
        menu.addRow(2, drawButton);

        stage.setScene(new Scene(menu, WIDTH, HEIGHT));
        stage.show();
    }
    public String getTitle(){
        return title;
    }

    private void drawFractal(int scale, int iters){
        Group root = new Group();

        var radius = scale*8;
        int x = WIDTH/2;
        int y = HEIGHT/2;

        Circle seed = new Circle(x,y,radius);
        seed.setStroke(Color.BLACK);
        seed.setFill(Color.TRANSPARENT);
        root.getChildren().add(seed);

        drawNorthCircle(x,y,radius, iters, root);
        drawWestCircle(x,y,radius, iters, root);
        drawEastCircle(x,y,radius, iters, root);

        stage.setScene(new Scene(root, HEIGHT, WIDTH));
        stage.show();
    }

    private void drawNorthCircle(int x, int y, float radius, int iters, Group root){
        if (iters <= 0 || radius /2 < 1)
            return;

        iters--;

        Circle circle = new Circle(x,y-radius,radius/2);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        root.getChildren().add(circle);

        drawNorthCircle(x, (int) (y-radius), radius/2, iters, root);
    }
    private void drawWestCircle(int x, int y, float radius, int iters, Group root){
        if (iters <= 0 || radius /2 < 1)
            return;

        iters--;

        Circle circle = new Circle(x-radius,y,radius/2);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        root.getChildren().add(circle);

        drawWestCircle((int)(x-radius), y, radius/2, iters, root);
    }
    private void drawEastCircle(int x, int y, float radius, int iters, Group root){
        if (iters <= 0 || radius /2 < 1)
            return;

        iters--;

        Circle circle = new Circle(x+radius,y,radius/2);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        root.getChildren().add(circle);

        drawEastCircle((int) (x+radius), y, radius/2, iters, root);
    }
}
