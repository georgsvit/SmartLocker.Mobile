package com.example.smartlockermobile.data.network

import com.example.smartlockermobile.data.network.dto.requests.LoginRequest
import com.example.smartlockermobile.data.network.dto.responses.JWTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest) : JWTokenResponse

}