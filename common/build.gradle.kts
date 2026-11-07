plugins {

}

repositories {

}

dependencies {
    compileOnly(libs.bundles.common.provided)
    compileOnlyApi(libs.bundles.common.loaded)
    api(libs.bundles.common.included)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["java"])
                
                groupId = "com.github.prostudios" 
                artifactId = "canoftrash"
                version = "1.0.0" 
            }
        }
    }
