/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.andre.ribeiro.servlet.todo;

import java.util.LinkedHashMap;

/**
 *
 * @author AndreRibeiro
 */
public class TaskDB {

    private static TaskDB instance = null;
    private LinkedHashMap<Integer,Task> tasks;

    public TaskDB() {
        tasks = new LinkedHashMap<Integer,Task>();
    }

    public static TaskDB instance() {

        return instance == null ? instance = new TaskDB() : instance;
    }

    public void add(Task t) {
        tasks.put(tasks.size()+1,t);
    }

    public LinkedHashMap<Integer,Task> getTasks() {
        return tasks;
    }
   
    public static void initDB(LinkedHashMap<Integer,Task> tasks){
        instance().tasks = tasks;
    }
    
    public static boolean isEnable(){
        return instance == null ? false : true;
    }
    
}
