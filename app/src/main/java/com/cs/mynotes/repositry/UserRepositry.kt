package com.cs.mynotes.repositry

import android.util.Log
import com.cs.mynotes.api.UserApi
import com.cs.mynotes.models.UserRequest
import com.cs.mynotes.utils.Constants.TAG
import javax.inject.Inject

class UserRepositry @Inject constructor (private val userApi: UserApi) {

    suspend fun registerUser(userRequest: UserRequest){
        val response = userApi.signUp(userRequest)
        Log.d(TAG, "registerUser: ${response.body().toString()}")
    }

    suspend fun loginUser(userRequest: UserRequest){
        val response = userApi.signIn(userRequest)
        Log.d(TAG, "loginUser: ${response.body().toString()}")
    }
}