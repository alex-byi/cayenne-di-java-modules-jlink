module org.example.apache.cayenne.module.second {
    requires org.apache.cayenne.di;

    exports org.example.apache.cayenne.module.second to org.apache.cayenne.di, org.example.apache.cayenne.module.main;

    provides org.apache.cayenne.di.spi.ModuleProvider with org.example.apache.cayenne.module.second.SecondModuleProvider;
}