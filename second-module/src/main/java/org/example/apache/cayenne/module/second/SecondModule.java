package org.example.apache.cayenne.module.second;

import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;

public class SecondModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(SecondInterface.class).to(SecondImpl.class);
    }
}
