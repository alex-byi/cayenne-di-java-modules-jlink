package org.example.apache.cayenne.module.first;

import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;

public class FirstModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(FirstInterface.class).to(FirstImpl.class);
    }
}
