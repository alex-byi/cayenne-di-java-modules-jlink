package org.example.apache.cayenne.module.first;

import org.apache.cayenne.di.Module;
import org.apache.cayenne.di.spi.ModuleProvider;

import java.util.Collection;
import java.util.Collections;

public class FirstModuleProvider implements ModuleProvider {

    @Override
    public Module module() {
        return new FirstModule();
    }

    @Override
    public Class<? extends Module> moduleType() {
        return FirstModule.class;
    }

    @Override
    public Collection<Class<? extends Module>> overrides() {
        return Collections.emptyList();
    }
}
