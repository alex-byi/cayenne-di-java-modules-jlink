package org.apache.cayenne.module.second;

import org.apache.cayenne.di.Module;

import java.util.Collection;
import java.util.Collections;

public class MainModuleProvider implements SecondModuleProvider {
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
