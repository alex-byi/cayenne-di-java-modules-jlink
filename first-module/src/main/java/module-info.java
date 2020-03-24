module org.example.apache.cayenne.module.first {
    requires org.apache.cayenne.di;

    exports org.example.apache.cayenne.module.first to org.apache.cayenne.di, org.example.apache.cayenne.module.main;

    provides org.apache.cayenne.di.spi.ModuleProvider with org.example.apache.cayenne.module.first.FirstModuleProvider;
}