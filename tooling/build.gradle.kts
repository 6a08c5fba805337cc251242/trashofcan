plugins {
    alias(libs.plugins.gradle)
    `kotlin-dsl`
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
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/6a08c5fba805337cc251242/trashofcan")
            credentials {
                username = providers.gradleProperty("gpr.user").getOrNull() ?: System.getenv("USERNAME")
                password = providers.gradleProperty("gpr.key").getOrNull() ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
