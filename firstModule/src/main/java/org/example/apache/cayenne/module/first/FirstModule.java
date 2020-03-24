package org.example.apache.cayenne.module.first;

import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;

public class FirstModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(FirstModuleProvider.class).to(MainModuleProvider.class);
    }

    public void printStr(){
        System.out.println("First Module work!!!");
    }
}
