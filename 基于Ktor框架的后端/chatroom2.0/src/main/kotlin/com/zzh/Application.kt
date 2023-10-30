package com.zzh

import com.zzh.di.mainModule
import com.zzh.plugins.*
import io.ktor.application.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(mainModule)
    } 
    configureSerialization()
    configureSockets()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
