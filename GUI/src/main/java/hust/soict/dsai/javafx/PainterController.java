package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.time.chrono.Era;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;
    @FXML
    private RadioButton Eraser;
    @FXML
    private RadioButton pen;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if (pen.isSelected()) {
            Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
            drawingAreaPane.getChildren().add(newCircle);
        }
        if (Eraser.isSelected()) {
            if (Eraser.isSelected()) {
                Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.WHITE);
                drawingAreaPane.getChildren().add(newCircle);
            }
        }
    }
}