package com.example.springboottutorial.global.extension

import org.slf4j.Logger

fun Logger.appInfo(message: String, vararg args: Any?) {
    val coloredMessage = "\u001B[32m[APP]\u001B[0m $message"
    this.info(coloredMessage, *args)
}

fun Logger.appWarn(message: String, vararg args: Any?) {
    val coloredMessage = "\u001B[33m[WARN]\u001B[0m $message"
    this.warn(coloredMessage, *args)
}

fun Logger.appError(message: String, vararg args: Any?) {
    val coloredMessage = "\u001B[31m[ERROR]\u001B[0m $message"
    this.error(coloredMessage, *args)
}

fun Logger.appDebug(message: String, vararg args: Any?) {
    val coloredMessage = "\u001B[34m[DEBUG]\u001B[0m $message"
    this.debug(coloredMessage, *args)
}


fun Logger.verbose(message: String, vararg args: Any?) {
    val coloredMessage = "\u001B[35m[VERBOSE]\u001B[0m $message"
    this.info(coloredMessage, *args)
}
