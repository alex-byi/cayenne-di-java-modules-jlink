module firstModule {
    requires cayenneDI;

    exports org.apache.cayenne.module.first to cayenneDI, main;

    provides org.apache.cayenne.di.spi.ModuleProvider with org.apache.cayenne.module.first.MainModuleProvider;
}