package com.example.application.views.tasks;

import com.example.application.components.TaskElement;
import com.example.application.data.Task;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TasksAccordion<T extends Task,
        E extends TaskElement> extends Accordion {

    private List<E> taskElements;

    public TasksAccordion(){
        close();
//        addTasks(tasks);
    }

    protected void addTasks(List<T> tasks, Class<E> eClass, String username){
        taskElements = new ArrayList<>();
        for (T task: tasks
             ) {
            try {
                Constructor<E> constructor = eClass.getConstructor(task.getClass(), String.class);
                E taskElement = constructor.newInstance(task, username);
                taskElements.add(taskElement);

                this.add(task.getTaskName(), taskElement.getTaskLayout());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void addTasks(List<T> tasks, Class<E> eClass){
        taskElements = new ArrayList<>();
        for (T task: tasks
        ) {
            try {
                Constructor<E> constructor = eClass.getConstructor(task.getClass());
                E taskElement = constructor.newInstance(task);
                taskElements.add(taskElement);

                this.add(task.getTaskName(), taskElement.getTaskLayout());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
