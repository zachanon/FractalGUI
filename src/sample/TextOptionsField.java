package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextOptionsField extends MenuField {

    private TextField field;
    private Label label;
    private String name;

    public TextOptionsField(String name, String label){
        this.name = name;
        this.label = new Label(label);
        field = new TextField();
    }

    public Group getSelf(){
        Group self = new Group();
        self.getChildren().addAll(label, field);

        return self;
    }

    public String getFieldValue(){
        return field.getText();
    }

    public String getName(){
        return name;
    }
}
