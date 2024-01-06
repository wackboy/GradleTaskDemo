import org.example.ShowPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property

class CustomPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        def extensionPlugin = project.extensions.create("custom", ShowPluginExtension)
        project.task("showCustomPlugin") {
            doLast {
//                println("${extensionPlugin.message.get()}")
                println("ha ha I'm custom Plugin")
            }
        }
    }
}
