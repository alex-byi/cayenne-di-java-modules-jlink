package org.example.apache.cayenne.module.first;

public class FirstImpl implements FirstInterface {

    @Override
    public void printMessage() {
        System.out.println("First module message");
    }

    @Override
    public String someString() {
        return "First module message";
    }
}
