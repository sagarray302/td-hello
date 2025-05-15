package com.talkdesk.hello.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class EchoServiceTest {
    private var echoService = EchoService()

    @Test
    @DisplayName("Should return a map with value equals the message from parameter")
    fun message() {
        val message = "Hello"
        val hello = echoService.getServiceString(message)
        Assertions.assertTrue(hello.containsValue(message))
    }
}
