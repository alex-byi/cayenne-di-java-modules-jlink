package org.example.apache.cayenne.module.second;

import org.apache.cayenne.di.Module;
import org.apache.cayenne.di.spi.ModuleProvider;

import java.util.Collection;
import java.util.Collections;

public class SecondModuleProvider implements ModuleProvider {

    @Override
    public Module module() {
        return new SecondModule();
    }

    @Override
    public Class<? extends Module> moduleType() {
        return SecondModule.class;
    }

    @Override
    public Collection<Class<? extends Module>> overrides() {
        return Collections.emptyList();
    }
}
