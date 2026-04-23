package com.hostmint.app;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongToAnyOf;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = HostMintApp.class, importOptions = DoNotIncludeTests.class)
class TechnicalStructureTest {

    @ArchTest
    static final ArchRule respectsTechnicalArchitectureLayers = layeredArchitecture()
        .consideringAllDependencies()
        // ADD THIS LINE: Ignore dependencies originating strictly from the root package (HostMintApp)
        .ignoreDependency(resideInAPackage("com.hostmint.app"), alwaysTrue())
        .layer("Config")
        .definedBy("..config..")
        .layer("Web")
        .definedBy("..web..")
        .layer("Service")
        .definedBy("..service..")
        .layer("Security")
        .definedBy("..security..")
        .layer("Persistence")
        .definedBy("..repository..")
        .layer("Domain")
        .definedBy("..domain..")
        .layer("Aop")
        .definedBy("..aop..")
        .whereLayer("Config")
        .mayNotBeAccessedByAnyLayer()
        .whereLayer("Web")
        .mayOnlyBeAccessedByLayers("Config")
        .whereLayer("Service")
        .mayOnlyBeAccessedByLayers("Web", "Config", "Aop")
        .whereLayer("Security")
        .mayOnlyBeAccessedByLayers("Config", "Service", "Web")
        .whereLayer("Persistence")
        .mayOnlyBeAccessedByLayers("Service", "Security", "Web", "Config")
        .whereLayer("Domain")
        .mayOnlyBeAccessedByLayers("Persistence", "Service", "Security", "Web", "Config", "Aop");
}
