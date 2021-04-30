package com.example.smartlockermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName

data class ToolResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("name")
    var Name: String,
    @SerializedName("description")
    var Description: String,
    @SerializedName("imgUrl")
    var ImgUrl: String,
    @SerializedName("accessLevel")
    var AccessLevel: Int,
    @SerializedName("serviceState")
    var ServiceState: String,
    @SerializedName("serviceBook")
    var ServiceBook: ServiceBookResponse,
    @SerializedName("locker")
    var Locker: LockerResponse
)