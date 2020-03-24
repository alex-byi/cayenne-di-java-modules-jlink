package org.example.apache.cayenne.module.second;

import org.apache.cayenne.di.DIBootstrap;
import org.apache.cayenne.di.Injector;
import org.apache.cayenne.di.Module;
import org.example.apache.cayenne.module.test.TestImpl;
import org.example.apache.cayenne.module.test.TestInterface;
import org.junit.Before;
import org.junit.Test;

public class SecondImplTest {

    private Injector injector;

    @Before
    public void setup(){
        Module module = binder -> binder.bind(TestInterface.class).to(TestImpl.class);
        injector = DIBootstrap.createInjector(module);
    }

    @Test
    public void test() {
        TestInterface testInterface = injector.getInstance(TestInterface.class);
        testInterface.someMethod();
    }
}