plugins {
    `javiersc-kotlin-multiplatform`
    `javiersc-publish-kotlin-multiplatform`
}

kotlin {
    explicitApi()

    jvm()

    sourceSets {
        commonMain

        commonTest {
            dependencies {
                libs.apply {
                    implementation(jetbrains.kotlin.kotlinTestCommon)
                    implementation(jetbrains.kotlin.kotlinTestJunit)
                    implementation(kotest.kotestAssertionsCore)
                }
            }
        }

        named("jvmTest")
    }
}
