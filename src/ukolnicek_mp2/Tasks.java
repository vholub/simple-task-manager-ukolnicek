/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukolnicek_mp2;

import java.time.LocalDate;

/**
 *
 * @author vholub
 */
public class Tasks {
    private String task;
    private LocalDate deadline;
    private String description;
    
    public String getTask(){
        return task;
    }
    
    public LocalDate getDeadline(){
        return deadline;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Tasks(String task, LocalDate deadline, String description) throws IllegalArgumentException{
        LocalDate today = LocalDate.now();
        
        if (task.length()<3)
            throw new IllegalArgumentException("Task is too short");
       
        if (deadline.isBefore(today)){
            ExpandableAlert expandableAlert = new ExpandableAlert();
            expandableAlert.beforeDeadline();
        }
        
        if(task.contains(";"))
            throw new IllegalArgumentException();
        
        this.task = task;
        this.deadline=deadline;
        this.description=description;
    }
   
   
    
    @Override
    public String toString(){
        return task;
    }
}
