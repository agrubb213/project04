import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;


/**
 * Created by 014291 on 2/14/2019.
 * Creates an Object list of the tasks class
 */
public class TaskList implements Iterable<Task> {
    /*
    private Scanner scan = new Scanner(System.in);
    public boolean updateTask(ArrayList<Task> getList) {
        boolean check = true;
        int checkTask = 0;
        //ask user if what id number they want to update
        System.out.println("What task would you like to update?(By ID number)");
        int taskId = scan.nextInt();
        scan.nextLine();
        for (Task getTask : getList) {
            // goes through each element in getList.
            if (taskId == getTask.getId()) {
                // if the number the user wants to check equals a task id then return true
                getList.remove(getTask);
                check = true;
            }
            if (checkTask > getList.size()) {
                // if the task your are asking for is larger then the task size then return false
                System.out.println("Input a valid task!");
                check = false;
            }
            checkTask++;
        }
        return check;
    }
    public void listTask(ArrayList<Task> getList){
        int idNum = 1;
        for (int i = 5; i>0;i--) {
            // organizes base on priority.
            for (Task getTask : getList) {
                // run all elements in task
                if (getTask.getTaskPri() == i) {
                    // if the priority equals the variable in the first for loop then out put that task.
                    System.out.println(idNum+") " + getTask.getTaskName()+ "\nDescription: " + getTask.getTaskDes()
                            + "\nPriority: " + i);
                    getTask.setId(idNum);
                    idNum++;
                }
            }
        }
    }
     */
    Scanner scan = new Scanner(System.in);
    ArrayList<Task> taskList = new ArrayList<>();
    ArrayList<Task> taskList2 = new ArrayList<>();
    int id= 1;

    void addTask() {
        // makes and adds a task to a list
        int taskPri = 0;
        boolean validInput = false;

        System.out.println("What is the name of your new task?");
        String askTask = scan.nextLine();

        System.out.println("What is the description of the task " + askTask + ":");
        String taskDes = scan.nextLine();

        while (!validInput) {
            //checking if user inputs a valid int for priority of task
            try {
                System.out.println("What is the priority of this task?(1-5)");
                taskPri = scan.nextInt();
                scan.nextLine();
                if (0 < taskPri & taskPri < 6) {
                    // checks if the task priority is between 1-5 then
                    validInput = true;
                } else {
                    System.out.println("input a valid priority number(1-5)");
                }

            } catch (Exception e) {
                scan.nextLine();
                System.out.println("invalid input try again!");
            }
        }
        // adding the new task to a list
        Task newTask = new Task(askTask, taskDes, taskPri, id);
        id++;
        taskList.add(newTask);
    }

    void removeTask() {
        // removes a task from the list
        int checkTask = 0;
        int taskId = 0;
        boolean validInput = false;

        while (!validInput) {
            // checks if user inputs a valid int for id number
            try {
                System.out.println("What task would you like to remove?(By ID number)");
                taskId = scan.nextInt();
                scan.nextLine();
                validInput = true;

            } catch (Exception e) {
                scan.nextLine();
                System.out.println("invalid input try again!");
            }
        }
        taskList2 = taskList;
        for (Task getTask : taskList2) {
            // run all elements in task list
            if (taskId == getTask.getId()) {
                taskList.remove(getTask);
            } else if (checkTask > taskList2.size()) {
                // if the for loop goes through more elements then the size of the list then tell the user it was not a valid input
                System.out.println("Input a valid task!");
                removeTask();
            }
            checkTask++;
        }
    }

    public void json(){
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter("tasks.json");
            gson.toJson(taskList,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    public void listTask(){
        Collections.sort(taskList);
        taskList.forEach((task) -> task.toDisplay());
    }
}
