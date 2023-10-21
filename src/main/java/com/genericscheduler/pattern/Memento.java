package com.genericscheduler.pattern;

/**
 * the object that is going to maintain the state of originator. Its just a POJO.
 */
public class Memento {
    private final int state;

    public Memento(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
