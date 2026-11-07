plugins {
    alias(libs.plugins.gradle)
    `kotlin-dsl`
    id(`maven-publish`)
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.grgit)
    implementation(libs.gson)
    implementation(libs.paperweight)
    implementation(libs.bundles.jackson) {
        exclude(group = "org.jetbrains.kotlin")
    }
}

gradlePlugin {
    plugins {
        website = "https://docs.preva1l.info/"
        vcsUrl = "https://github.com/Finally-A-Decent/Trashcan.git"

        create("trashcan") {
            id = "info.preva1l.trashcan"
            implementationClass = "info.preva1l.trashcan.TrashcanPlugin"

            displayName = "Trashcan"
            description = "Build tooling to help with the Trashcan library and other general tasks."
            tags = listOf("minecraft", "bukkit", "paper", "fabric", "library", "tooling")
        }
    }
}

tasks {
    withType<Javadoc> {
        (options as StandardJavadocDocletOptions).tags(
            "apiNote:a:API Note:",
            "implSpec:a:Implementation Requirements:",
            "implNote:a:Implementation Note:"
        )
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])          
        }
    }
}
