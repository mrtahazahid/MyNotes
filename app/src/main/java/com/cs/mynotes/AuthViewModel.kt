package com.cs.mynotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs.mynotes.models.UserRequest
import com.cs.mynotes.repositry.UserRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repositry: UserRepositry) : ViewModel(){

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            repositry.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            repositry.loginUser(userRequest)
        }
    }
}