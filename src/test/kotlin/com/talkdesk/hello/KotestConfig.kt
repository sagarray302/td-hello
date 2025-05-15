package com.talkdesk.hello

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.listeners.Listener
import io.kotest.extensions.htmlreporter.HtmlReporter
import io.kotest.extensions.junitxml.JunitXmlReporter
import io.kotest.extensions.spring.SpringExtension

class KotestConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)

    override fun listeners(): List<Listener> =
        listOf(
            JunitXmlReporter(
                includeContainers = false,
                useTestPathAsName = true,
            ),
            HtmlReporter(),
        )
}
