package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Homemenu {
    private Scene scene;
    private GridPane root;

    private ArrayList<GenerativeMenu> menus = new ArrayList<>(){
        {
            add(new CircleMenu());
        }
    };

    private int HEIGHT=600, WIDTH=400, scale = 40;

    public Homemenu(){
        this.root = new GridPane();
        buildMenu();

        scene = new Scene(root, WIDTH, HEIGHT);
    }

    public Scene getScene(){ return scene; }

    private void buildMenu(){
        Button menuButton;

        int count = 0;
        for(var menu : menus){
            menuButton = new Button(menu.getTitle());
            menuButton.setOnAction(action -> {
                    menu.drawMenu();
            });
            root.addRow(count, menuButton);
            count++;
        }

        HEIGHT = scale*menus.size();
        root.setVgap(scale);
        root.setAlignment(Pos.CENTER);
    }
}
