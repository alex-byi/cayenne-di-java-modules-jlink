module secondModule {
    requires cayenneDI;

    exports org.apache.cayenne.module.second to cayenneDI, main;

    provides org.apache.cayenne.di.spi.ModuleProvider with org.apache.cayenne.module.second.MainModuleProvider;
}