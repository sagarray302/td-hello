package com.talkdesk.hello.controller

import com.talkdesk.hello.services.EchoService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/echo")
class EchoController(
    private val echoService: EchoService,
) {
    @GetMapping(
        value = ["/{message}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun echoMessage(
        @PathVariable("message") message: String,
    ): ResponseEntity<Map<String, String>> {
        val result = this.echoService.getServiceString(message)
        return ResponseEntity.ok(result)
    }
}
