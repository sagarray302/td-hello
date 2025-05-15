package com.talkdesk.hello.services

import org.springframework.stereotype.Service

@Service
class EchoService {
    fun getServiceString(message: String): Map<String, String> = mapOf("message" to message)
}
