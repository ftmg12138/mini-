package com.zzh.chatroom0.data.remote

import com.zzh.chatroom0.domain.model.Message
//这个接口用来获取所有的消息
interface MessageService {
    suspend fun getAllMessages(): List<Message>
    //这个是一个伴生对象，用来存储一些常量
    companion object {
        const val BASE_URL = "http://10.21.205.202:8080/"//这个是服务器的地址
    }
    //这个是一个枚举类，用来存储所有的url
    sealed class Endpoints(val url: String) {
        object GetAllMessages : Endpoints("$BASE_URL/messages")
    }

}