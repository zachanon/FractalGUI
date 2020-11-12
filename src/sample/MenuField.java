package sample;

import javafx.scene.Group;
import javafx.scene.Node;

public abstract class MenuField extends Group {

    public abstract String getName();
    public abstract Group getSelf();
    public abstract Object getFieldValue();
}
