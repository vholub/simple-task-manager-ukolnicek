/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukolnicek_mp2;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author vholub
 */
public class TaskManager {
    
    private String path = "tasks.csv";
    
    private ObservableList<Tasks> tasks = FXCollections.observableArrayList();

    public ObservableList<Tasks> getTasks() {
            return tasks;
    }
   
    public void add(Tasks task){
        tasks.add(task);
    }
    
    public void remove(Tasks task){
        tasks.remove(task);
    }
    
    public void save() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                for(Tasks o : (getTasks())) {
                        bw.write(o.getTask() + ";" + 
                                o.getDeadline().toString() + ";" + 
                                o.getDescription().replace("\n", "\\n"));
                        bw.newLine();
                }
        }
    }
    
    public void load() throws IOException, ParseException {
            File file = new File(path);
            if (file.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                            List<Tasks> tempTasks = new ArrayList<>();
                            String line;
                            while ((line = br.readLine()) != null) {
                                    String[] parts = line.split(";");
                                    String task = parts[0];
                                    LocalDate deadline = LocalDate.parse(parts[1]);
                                    String desc = parts[2].replace("\\n", "\n");
                                    tempTasks.add(new Tasks(task, deadline, desc));
                            }
                            tasks.clear();
                            tasks.addAll(tempTasks);
                    }
            } else
                    tasks.clear();
        }
}
