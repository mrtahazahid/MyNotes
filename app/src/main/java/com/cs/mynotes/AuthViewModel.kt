package com.cs.mynotes

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs.mynotes.models.UserRequest
import com.cs.mynotes.models.UserResponse
import com.cs.mynotes.repositry.UserRepositry
import com.cs.mynotes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repositry: UserRepositry) : ViewModel(){

    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
    get() = repositry.userResponseLiveData

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

    fun validateCredentials(userName : String, email : String, password : String, isLogin : Boolean) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if((!isLogin && TextUtils.isEmpty(userName)) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            result = Pair(false, "Please provide credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result = Pair(false, "Please provide valid email")
        }
        else if(password.length <=5){
            result = Pair(false, "Password length should be greater than 5")
        }

        return result
    }
}