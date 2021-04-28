package com.example.smartlockermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class JWTokenResponse(
    @SerializedName("token")
    var AccessToken: String,
    @SerializedName("expireDate")
    var ExpiresAt: Date,
    @SerializedName("id")
    var Id: String,
    @SerializedName("firstname")
    var Name: String,
    @SerializedName("lastname")
    var Surname: String,
    @SerializedName("Role")
    var RoleName: String
)