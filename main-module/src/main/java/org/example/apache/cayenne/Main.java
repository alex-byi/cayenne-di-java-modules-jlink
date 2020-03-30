package org.example.apache.cayenne;

import org.apache.cayenne.di.DIBootstrap;
import org.apache.cayenne.di.Injector;
import org.apache.cayenne.di.spi.ModuleLoader;
import org.apache.cayenne.di.spi.ModuleProvider;
import org.example.apache.cayenne.module.first.FirstInterface;
import org.example.apache.cayenne.module.second.SecondInterface;
import com.example.Calc;


public class Main {

    public static void main(String[] args) {

        ModuleLoader loader = new ModuleLoader();
        Injector injector = DIBootstrap.createInjector(loader.load(ModuleProvider.class));

        FirstInterface firstInterface = injector.getInstance(FirstInterface.class);
        firstInterface.printMessage();

        SecondInterface secondInterface = injector.getInstance(SecondInterface.class);
        secondInterface.printMessage();

        System.out.println(Calc.sum(2, 2));
    }
}
