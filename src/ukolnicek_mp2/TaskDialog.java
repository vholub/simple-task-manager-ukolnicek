/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukolnicek_mp2;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * * @author vholub
 */
public class TaskDialog extends Stage {
    
    private Tasks task = null;
    
    public Tasks getTask(){
        return task;
    }
    
    public TaskDialog(Window okno) {
        setTitle("New task");
        setWidth(450);
        setHeight(350);

        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(okno);
        setScene(createScene());
}
    
    private Scene createScene() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        
        final TextField taskTextField = new TextField();
        Label taskLabel = new Label("Task");
        Label descriptionLabel = new Label("Description");
        TextArea descriptionArea = new TextArea();
        Label dateLabel = new Label("Deadline:");

        DatePicker dpick = new DatePicker();
        
        taskLabel.setMinWidth(100);
        
        grid.add(taskLabel, 0, 0);
        grid.add(taskTextField, 1, 0);
        grid.add(dateLabel, 0, 1);
        grid.add(dpick, 1, 1);
        grid.add(descriptionLabel, 0, 2);
        grid.add(descriptionArea, 1, 2);

        descriptionArea.setWrapText(true);
        
        Button tlacitko = new Button("OK");
        tlacitko.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                        LocalDate ldate = dpick.getValue();
                        
                        try{
                            if(ldate != null || taskTextField.getText()!=""){
                                task = new Tasks(taskTextField.getText(), ldate, 
                                        descriptionArea.getText());
                                hide();
                            }
                        }
                        catch (Exception eee){
                            ExpandableAlert expandableAlert = new ExpandableAlert();
                            expandableAlert.ops();
                        }
                        
                }
        });

        box.getChildren().addAll(grid, tlacitko);
        return new Scene(box);
}
}
