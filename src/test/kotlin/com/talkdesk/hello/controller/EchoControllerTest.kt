package com.talkdesk.hello.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class EchoControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("Give a request with a parameter should return the message and the status ok")
    fun requestSuccessful() {
        this.mockMvc.perform(get("/echo/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk)
            .andExpect(content().string("{\"message\":\"hello\"}"))
    }

    @Test
    @DisplayName("Given a request without parameter should not found")
    fun requestFail() {
        this.mockMvc.perform(get("/echo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound)
    }
}
