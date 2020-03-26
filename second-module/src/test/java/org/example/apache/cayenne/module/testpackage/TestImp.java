package org.example.apache.cayenne.module.testpackage;

import org.example.apache.cayenne.module.first.FirstInterface;

public class TestImp implements FirstInterface {

    @Override
    public void printMessage() {
        System.out.println("Test impl message");
    }

    @Override
    public String someString() {
        return "Test impl message";
    }
}
