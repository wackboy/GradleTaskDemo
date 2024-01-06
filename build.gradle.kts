// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
}

apply<CustomPlugin>()
//the<org.example.ShowPluginExtension>().message.set("Hi from Gradle")

//interface GreetingPluginMultiExtension {
//    val message: Property<String>
//    val greeter: Property<String>
//}
//
//abstract class GreetingPluginExtension {
//    abstract val message: Property<String>
//
//    init {
//        message.convention("Hello from GreetingPlugin")
//    }
//}
//
//class GreetingPlugin : Plugin<Project> {
//    override fun apply(project: Project) {
//        val extension = project.extensions.create<GreetingPluginExtension>("greeting")
//        val extensionMulti = project.extensions.create<GreetingPluginMultiExtension>("greetingMulti")
//        project.task("hello") {
//            doFirst {
//                println(extension.message.get())
//                println("${extensionMulti.message.get()} from ${extensionMulti.greeter.get()}")
//            }
//        }
//    }
//}
//apply<GreetingPlugin>()
//
//the<GreetingPluginExtension>().message.set("Hi from Gradle")
//
//configure<GreetingPluginMultiExtension>() {
//    message.set("What's up")
//    greeter.set("Man!!")
//}


/**
 * Working with files in custom tasks and plugins
 */
abstract class GreetingToFileTask : DefaultTask() {

    @get:OutputFile
    abstract val destination: RegularFileProperty

    @TaskAction
    fun greet() {
        val file = destination.get().asFile
        file.parentFile.mkdirs()
        file.writeText("Hello!")
    }

}

val greetingFile = objects.fileProperty()
tasks.register<GreetingToFileTask>("greet") {
    destination.set(greetingFile)
}

tasks.register("sayGreeting") {
    dependsOn("greet")
    doLast {
        val file = greetingFile.get().asFile
        println("${file.readText()} (file: ${file.name})")
    }
}
// kind of lazy evaluation
greetingFile.set(layout.buildDirectory.file("hello.txt"))

























