package com.wk.interview;

public class Node<T> {

    private T value;

    public Node<T> next;

    public boolean notEmpty(){
        return value != null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
