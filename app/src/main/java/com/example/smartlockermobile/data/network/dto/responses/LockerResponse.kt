package com.example.smartlockermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName

data class LockerResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("login")
    var Login: String
)