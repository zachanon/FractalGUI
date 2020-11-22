package sample;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class OneDimensionAutomata extends GenerativeMenu {

    private Stage stage;
    private final String title = "One-Dimension Automata";
    private int HEIGHT=700, WIDTH=1000;
    private ArrayList<TextField> ruleFields = new ArrayList<>(){
        {
            //Eight rules possible
            for(int i = 0; i < 8; i++)
                add(new TextField("0"));
        }
    };
    private ArrayList<Label> labels = new ArrayList<>(){
        {
            add(new Label("Rule 000: "));
            add(new Label("Rule 001: "));
            add(new Label("Rule 010: "));
            add(new Label("Rule 011: "));
            add(new Label("Rule 100: "));
            add(new Label("Rule 101: "));
            add(new Label("Rule 110: "));
            add(new Label("Rule 111: "));
        }
    };

    private final ArrayList<Boolean> defaultRules = new ArrayList<>(){
        {
            add(false);
            add(true);
            add(true);
            add(true);
            add(true);
            add(false);
            add(false);
            add(false);
        }
    };


    public OneDimensionAutomata(){
        stage = new Stage();
        stage.setTitle(title);

    }
    @Override
    public void drawMenu() {
        GridPane menu = new GridPane();

        Button drawButton = new Button("Draw");
        drawButton.setOnAction(actionEvent -> {
            try {
                var input = parseTextInput();
                if (input.size() > 0)
                    drawAutomata(parseTextInput());
            }
            catch(NumberFormatException exception){
                throwErrorPopup("Error: Improper input. Must be 0 or 1");
            }

        });
        Button defaultButton = new Button("Draw Default Ruleset");
        defaultButton.setOnAction(actionEvent -> {
            drawAutomata(defaultRules);
        });

        for(int i=0; i < ruleFields.size(); i++)
            menu.addRow(i, labels.get(i), ruleFields.get(i));

        menu.addRow(ruleFields.size()+1, drawButton, defaultButton);

        stage.setScene(new Scene(menu, WIDTH, HEIGHT));
        stage.show();
    }

    private void drawAutomata(ArrayList<Boolean> ruleset){
        Group root = new Group();
        int numIters = HEIGHT / 4;
        int numblocks = WIDTH /4;

        ArrayList<Boolean> blocks = new ArrayList<>(){{
           for(int i = 0; i < numblocks; i++)
               add(false);
        }};

        //seed
        blocks.set(numblocks/2, true);

        drawBlocks(numIters, blocks, ruleset, root);

        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }

    private void drawBlocks(int iter, ArrayList<Boolean> blocks, ArrayList<Boolean> ruleset, Group root){

        int y = HEIGHT-iter*4;
        if(iter < 1)
            return;
        iter--;

        Rectangle block;
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i)){
                block = new Rectangle(i*4, y, 4, 4);
                root.getChildren().add(block);
            }
            else{
                block = new Rectangle(i*4, y, 4, 4);
                block.setStroke(Color.BLACK);
                block.setFill(Color.TRANSPARENT);
                root.getChildren().add(block);
            }
        }

        drawBlocks(iter, applyRules(ruleset, blocks), ruleset, root);
    }

    private ArrayList<Boolean> applyRules(ArrayList<Boolean> rules, ArrayList<Boolean> blocks){
        ArrayList<Boolean> newBlocks = new ArrayList<>();

        //pad the starting window
        ArrayList<Boolean> window = new ArrayList<>(){
            {
                add(false);
                add(blocks.get(0));
                add(blocks.get(1));
            }
        };

        newBlocks.add(parseRule(rules, window));

        for(int i = 0; i < blocks.size()-2; i++){

            //clunky
            window = new ArrayList<>();
            window.add(blocks.get(i));
            window.add(blocks.get(i+1));
            window.add(blocks.get(i+2));

            newBlocks.add(parseRule(rules, window));
        }

        window = new ArrayList<>();
        window.add(blocks.get(blocks.size()-2));
        window.add(blocks.get(blocks.size()-1));
        //zero pad
        window.add(false);
        newBlocks.add(parseRule(rules, window));

        return newBlocks;
    }

    public boolean parseRule(ArrayList<Boolean> rules, ArrayList<Boolean> window){

        //1--
        if(window.get(0)){
            //11-
            if(window.get(1)){
                //111
                if(window.get(2)){
                    return rules.get(7);
                }
                //110
                else return rules.get(6);
            }
            //10-
            else {
                //101
                if(window.get(2)){
                    return rules.get(6);
                }
                //100
                else return rules.get(4);
            }
        }
        //0--
        else {
            //01-
            if(window.get(1)){
                //011
                if(window.get(2)){
                    return rules.get(3);
                }
                //010
                else return rules.get(2);
            }
            //00-
            else {
                //001
                if(window.get(2)){
                    return rules.get(1);
                }
                //000
                else return rules.get(0);
            }
        }

    }

    private ArrayList<Boolean> parseTextInput() {
        ArrayList<Boolean> ruleset = new ArrayList<>();
        for(var field : ruleFields) {
            if(!field.getText().equals("1") && !field.getText().equals("0")) {
                throwErrorPopup("Error: Input must be a 0 or 1");
                return new ArrayList<>();
            }
            ruleset.add(field.getText().equals("1"));
        }
        return ruleset;
    }

    private void throwErrorPopup(String errorMessage){
        Stage errorPopup = new Stage();
        Label message = new Label(errorMessage);
        message.setAlignment(Pos.CENTER);
        errorPopup.setScene(new Scene(message, 300, 300));
        errorPopup.show();
    }

    public String getTitle(){
        return title;
    }
}
