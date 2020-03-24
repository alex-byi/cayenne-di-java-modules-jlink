import org.example.apache.cayenne.module.second.MainModuleProvider;

module secondModule {
    requires cayenneDI;

    exports org.example.apache.cayenne.module.second to cayenneDI, mainModule;

    provides org.apache.cayenne.di.spi.ModuleProvider with MainModuleProvider;
}