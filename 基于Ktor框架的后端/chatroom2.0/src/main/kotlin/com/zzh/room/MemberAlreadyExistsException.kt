package com.zzh.room

class MemberAlreadyExistsException: Exception() {
    override val message: String?
        get() = "已经存在该用户"
}