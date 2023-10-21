package com.genericscheduler.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 *  the object that keeps track of multiple memento. Like maintaining savepoints.
 */
public class Caretaker {

    private List<Memento> history = new ArrayList<>();

    public void addMemento(Memento memento) {
        history.add(memento);
    }

    public Memento getByIndex(int index) {
        return history.get(index);
    }
}
