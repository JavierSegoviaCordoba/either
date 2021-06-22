plugins { `kotlin-dsl` }

repositories {
    mavenCentral()
    jcenter()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    pluginLibs.apply {
        implementation(javiersc.massiveCatalogs.pluginsAccessors)

        implementation(javiersc.gradlePlugins.allProjects)
        implementation(javiersc.gradlePlugins.changelog)
        implementation(javiersc.gradlePlugins.codeAnalysis)
        implementation(javiersc.gradlePlugins.codeFormatter)
        implementation(javiersc.gradlePlugins.dependencyUpdates)
        implementation(javiersc.gradlePlugins.docs)
        implementation(javiersc.gradlePlugins.kotlinMultiplatform)
        implementation(javiersc.gradlePlugins.publishKotlinMultiplatform)
        implementation(javiersc.gradlePlugins.nexus)
        implementation(javiersc.gradlePlugins.readmeBadges)
        implementation(javiersc.gradlePlugins.versioning)

        implementation(android.toolsBuild.gradle)
        implementation(jetbrains.kotlin.kotlinGradlePlugin)
        implementation(jetbrains.kotlin.kotlinSerialization)
        implementation(jetbrains.kotlinx.binaryCompatibilityValidator)
    }
}
