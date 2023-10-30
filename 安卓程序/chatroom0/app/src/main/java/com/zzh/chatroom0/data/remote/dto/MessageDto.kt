package com.zzh.chatroom0.data.remote.dto

import com.zzh.chatroom0.domain.model.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.Date
//这个文件是用来定义数据传输对象的，这个对象是用来在网络上传输的，所以需要序列化
@Serializable
data class MessageDto(
    val text: String,
    val timestamp: Long,
    val username: String,
    val id: String
){
    fun toMessage() : Message {
        val date = Date(timestamp)
        val formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date)
        return Message(
            text = text,
            formattedTime = formattedDate,
            username = username
        )
    }
}

