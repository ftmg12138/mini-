package com.zzh.di

import com.zzh.data.MessageDataSource
import com.zzh.data.MessageDataSourceImpl
import com.zzh.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

//for dependency injection

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("message_db_zzh")
    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single {
        RoomController(get())
    }
}