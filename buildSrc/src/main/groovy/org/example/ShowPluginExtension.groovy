package org.example

import org.gradle.api.provider.Property

abstract class ShowPluginExtension {
    abstract final Property<String> message
    ShowPluginExtension() {
        message.convention("Hello from showPluginExtension")
    }
}
