/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.andre.ribeiro.servlet.todo;

/**
 *
 * @author AndreRibeiro
 */
class Task {
    
    String taskDescription;

    public Task() {
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
    
    
}
