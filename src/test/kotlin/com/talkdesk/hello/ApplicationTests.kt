package com.talkdesk.hello

import io.kotest.core.spec.style.WordSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTests : WordSpec() {
    init {
        "contextLoads" should {
            "init without errors" {
            }
        }
    }
}
