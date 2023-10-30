package com.zzh.chatroom0.presentation.username

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
//这部分代码是用来处理用户输入的用户名的，这里我们使用了HiltViewModel注解，这样我们就可以在ViewModel中使用依赖注入了。
@HiltViewModel
class  UsernameViewModel @Inject constructor() : androidx.lifecycle.ViewModel() {

    private val _usernameText = mutableStateOf("")//这里我们使用了mutableStateOf来保存用户输入的用户名
    val usernameText: State<String> = _usernameText//这里我们使用了State来保存用户输入的用户名

    private val _onJoinChat = MutableSharedFlow<String>()
    val onJoinChat = _onJoinChat.asSharedFlow()

    fun onUsernameChange(username: String) {
        _usernameText.value = username
    }

    fun ClearText(){//清空输入框
        viewModelScope.launch {
            _usernameText.value = ""
        }
    }

    fun onJoinClick() {
        viewModelScope.launch {
            if(usernameText.value.isNotBlank()) {//验证用户名是否为空，此处可增加更多的验证逻辑
                _onJoinChat.emit(usernameText.value)
            }
        }
    }
}