package org.apache.cayenne.module.first;

import org.apache.cayenne.di.Module;

import java.util.Collection;
import java.util.Collections;

public class MainModuleProvider implements FirstModuleProvider {
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
