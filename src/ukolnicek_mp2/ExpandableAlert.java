/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukolnicek_mp2;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author vholub
 */
public class ExpandableAlert {
    public void ops(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Task hasn't been added!");

        alert.showAndWait();
    }
    
    public void beforeDeadline(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Be careful!");
        alert.setHeaderText(null);
        alert.setContentText("You are way before deadline, hurry up!");

        alert.showAndWait();
    }
    
    public void fileIsCorrupted(Exception corrupted){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("File cannot be opened");
        alert.setContentText("File tasks.csv is corrupted, try to delete it! \n"
                + "Application cannot work properly");

        alert.showAndWait();
    }
}
