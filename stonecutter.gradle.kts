plugins {
    id("dev.kikugie.stonecutter")
    id("me.modmuss50.mod-publish-plugin") version "0.8.4"
}
stonecutter active "1.21.1-fabric"

val modVersion = property("mod.version")!!.toString()

publishMods {
    version = modVersion

    changelog = """
# ${rootProject.name} v$modVersion
${System.getenv("CHANGELOG")}
""".trimIndent()

    type = when {
        "alpha" in modVersion.lowercase() -> ALPHA
        "beta" in modVersion.lowercase() -> BETA
        else -> STABLE
    }
}

tasks.named("publishMods") {
    doFirst {
        val changelog = System.getenv("CHANGELOG")
        if (changelog.isNullOrBlank()) {
            error("Environment variable CHANGELOG is not set or empty. Aborting publish.")
        }
    }
}


allprojects {
    repositories {
        mavenCentral()
        mavenLocal()

        // NeoForge
        maven("https://maven.neoforged.net/releases")
        // Fabric
        maven("https://maven.fabricmc.net/")
        // Forge
        maven("https://files.minecraftforge.net/maven")

        // Stonecutter, FletchingTable
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.kikugie.dev/snapshots")

        // Kotlin for Forge
        maven("https://thedarkcolour.github.io/KotlinForForge/")

        // Yet Another Config Lib
        maven("https://maven.isxander.dev/releases")

        // Mod Menu
        maven("https://maven.terraformersmc.com/")

        // Quilt
        maven("https://maven.quiltmc.org/repository/release/")

        // Placeholder API
        maven("https://maven.nucleoid.xyz/")
    }
}