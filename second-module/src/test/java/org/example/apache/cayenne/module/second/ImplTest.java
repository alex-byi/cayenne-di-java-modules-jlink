package org.example.apache.cayenne.module.second;

import org.apache.cayenne.di.DIBootstrap;
import org.apache.cayenne.di.Injector;
import org.apache.cayenne.di.Module;
import org.example.apache.cayenne.module.first.FirstInterface;
import org.example.apache.cayenne.module.testpackage.TestImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImplTest {

    private Injector injector;

    @Before
    public void setup() {
        Module module = binder -> {
            binder.bind(FirstInterface.class).to(TestImp.class);
        };
        injector = DIBootstrap.createInjector(module);
    }

    @Test
    public void test() {

        FirstInterface instance = injector.getInstance(FirstInterface.class);
        String string = instance.someString();

        instance.printMessage();
        Assert.assertEquals(string, "Test impl message");

    }
}