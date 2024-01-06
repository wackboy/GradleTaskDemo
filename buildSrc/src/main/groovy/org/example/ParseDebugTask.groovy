import groovy.xml.XmlUtil
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ParseDebugTask extends DefaultTask {

    @TaskAction
    void doAction() {
        def file = new File(project.buildDir, "/intermediates/merged_manifests/debug/AndroidManifest.xml")
        if (!file.exists()) {
            println("TAG: 文件不存在！")
            return
        }
        def fileContent = file.getText()
        removePermission(file, fileContent)
    }

    void removePermission(File file, String fileContent) {
        fileContent = fileContent.replace("android.permission.READ_PHONE_STATE", "")
        println(fileContent)
        file.write(fileContent)
    }

    void addPermission(File file, String fileContent) {
        def rootNode = new XmlParser().parse(fileContent)
        rootNode.appendNode("uses-permission",
                ["xml:android": "http://schemas.android.com/apk/res/android",
                 "android:name": "android.permission.INTERNET"])
        def updateXmlContent = XmlUtil.serialize(rootNode)
        file.write(updateXmlContent)
    }

}