import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 移除manifest中一些权限：android.permission.READ_PHONE_STATE
 */
class ManifestDemoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // 在afterEvaluate 配置完成之后才能拿到完成的task的有向图
        project.afterEvaluate {
            def mergeDebugResources = project.tasks.findByName("mergeDebugResources")
            project.tasks.forEach { task ->
                println("TAGHH: ${task.name}")
            }
            if (mergeDebugResources != null) {
                def parseDebugTask = project.tasks.create("ParseDebugTask", ParseDebugTask.class)
                mergeDebugResources.finalizedBy(parseDebugTask)
            }
        }
    }
}