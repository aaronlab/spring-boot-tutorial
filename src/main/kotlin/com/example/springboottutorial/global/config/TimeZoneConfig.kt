package com.example.springboottutorial.global.config

import com.example.springboottutorial.global.extension.appInfo
import com.example.springboottutorial.global.util.logger
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.TimeZone

@Component
class TimeZoneConfig {
    private val log = logger()

    @PostConstruct
    fun setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        log.appInfo("Application TimeZone set to UTC")
    }
}