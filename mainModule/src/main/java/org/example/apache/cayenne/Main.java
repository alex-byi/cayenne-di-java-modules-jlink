package org.example.apache.cayenne;

import org.apache.cayenne.di.DIBootstrap;
import org.apache.cayenne.di.Injector;
import org.apache.cayenne.di.spi.ModuleLoader;
import org.apache.cayenne.di.spi.ModuleProvider;
import org.example.apache.cayenne.module.second.SecondModule;
import org.example.apache.cayenne.module.second.SecondModuleProvider;
import org.example.apache.cayenne.module.first.FirstModule;
import org.example.apache.cayenne.module.first.FirstModuleProvider;

public class Main {

    public static void main(String[] args) {

        ModuleLoader loader = new ModuleLoader();

        Injector injector = DIBootstrap.createInjector(loader.load(ModuleProvider.class));

        FirstModuleProvider firstModuleProviderInstance = injector.getInstance(FirstModuleProvider.class);

        FirstModule firstModule = (FirstModule) firstModuleProviderInstance.module();

        firstModule.printStr();

        SecondModuleProvider secondModuleProviderInstance = injector.getInstance(SecondModuleProvider.class);

        SecondModule secondModule = (SecondModule) secondModuleProviderInstance.module();

        secondModule.printStr();

    }
}
