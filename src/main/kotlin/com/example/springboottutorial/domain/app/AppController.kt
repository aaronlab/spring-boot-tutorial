package com.example.springboottutorial.domain.app

import com.example.springboottutorial.global.annotation.Public
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "handshake")
@RestController
class AppController {
    @Public
    @GetMapping()
    fun home() = "Hello world!"
}
