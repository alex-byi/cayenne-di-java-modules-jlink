import org.example.apache.cayenne.module.first.MainModuleProvider;

module firstModule {
    requires cayenneDI;

    exports org.example.apache.cayenne.module.first to cayenneDI, mainModule;

    provides org.apache.cayenne.di.spi.ModuleProvider with MainModuleProvider;
}