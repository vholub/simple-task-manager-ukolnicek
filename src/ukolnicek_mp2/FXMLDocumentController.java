/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukolnicek_mp2;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 *
 * @author vholub
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label taskLabel;
    
    @FXML
    private TextArea description;
    
    @FXML
    private Label date;
    
    
    @FXML
    private ListView<Tasks> tasksListView;
    
    private TaskManager taskManager = new TaskManager();
    
    @FXML
    public void handleAddButton(ActionEvent event) throws IOException{
        TaskDialog dialog = new TaskDialog(date.getScene().getWindow());
        dialog.showAndWait();
        Tasks newTask2 = dialog.getTask();
        if(newTask2 != null){
            taskManager.add(newTask2);
            taskManager.save();
        }
        if(!taskManager.getTasks().isEmpty()){
            tasksListView.getSelectionModel().select(newTask2);
        }
    }
    
    @FXML
    public void handleRemoveButton(ActionEvent event){
        Tasks chosen = (Tasks)tasksListView.getSelectionModel().getSelectedItem();
        if (chosen != null){
            taskManager.remove(chosen);
            
            try {
                taskManager.save();
            } catch (Exception e) {
            }
            
            if(!taskManager.getTasks().isEmpty()){
                tasksListView.getSelectionModel().select(taskManager.getTasks().size()-1);
            }
        }
        
        if(taskManager.getTasks().isEmpty()){
            taskLabel.setText("No tasks in the list.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        description.setWrapText(true);
        
        tasksListView.setItems(taskManager.getTasks());
        if (!taskManager.getTasks().isEmpty())
            tasksListView.getSelectionModel().select(0);
        
        try {
            taskManager.load();
        } catch (IOException | ParseException ex) {
        }
        
        tasksListView.setItems(taskManager.getTasks());
        
        tasksListView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Tasks>() {

                @Override
                public void changed(ObservableValue<? extends Tasks> ov, 
                        Tasks oldValue, Tasks newValue) {

                    String taskString="", dateString="", descString="";
                    if (newValue != null) {
                        taskString=newValue.getTask();
                        dateString=newValue.getDeadline().toString();
                        descString=newValue.getDescription();
                    }
                    taskLabel.setText(taskString);
                    date.setText(dateString);
                    description.setText(descString);
                }
            });
        
        
        if(!taskManager.getTasks().isEmpty()){
            tasksListView.getSelectionModel().select(0);
        }
        else{
            taskLabel.setText("No tasks in the list.");
        }
    }
}
