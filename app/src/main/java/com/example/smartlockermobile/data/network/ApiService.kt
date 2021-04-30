package com.example.smartlockermobile.data.network

import com.example.smartlockermobile.data.network.dto.requests.LoginRequest
import com.example.smartlockermobile.data.network.dto.responses.JWTokenResponse
import com.example.smartlockermobile.data.network.dto.responses.ToolResponse
import retrofit2.http.*

interface ApiService {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest) : JWTokenResponse

    @GET(Constants.TOOL_URL)
    suspend fun getTools(@Header("Authorization") token: String) : List<ToolResponse>

    @GET(Constants.TOOL_URL + Constants.TOOL_ID_URL)
    suspend fun getToolById(@Path("toolId") toolId: String, @Header("Authorization") token: String) : ToolResponse
}