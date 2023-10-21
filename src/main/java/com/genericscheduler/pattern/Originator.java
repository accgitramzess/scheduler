package com.genericscheduler.pattern;

/**
 * the object for which the state is to be saved. It creates the memento and uses it in future to undo.
 */
public class Originator {

    private int state;

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }
}
