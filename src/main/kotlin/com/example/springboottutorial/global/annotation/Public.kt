package com.example.springboottutorial.global.annotation

import io.swagger.v3.oas.annotations.security.SecurityRequirements

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SecurityRequirements(value = [])
annotation class Public
