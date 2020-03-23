package org.apache.cayenne.module.second;

import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;

public class SecondModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(SecondModuleProvider.class).to(MainModuleProvider.class);
    }

    public void printStr(){
        System.out.println("Second Module work!!!");
    }
}
