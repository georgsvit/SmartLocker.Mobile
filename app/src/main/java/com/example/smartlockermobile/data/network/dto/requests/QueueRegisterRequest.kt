package com.example.smartlockermobile.data.network.dto.requests

import com.google.gson.annotations.SerializedName

class QueueRegisterRequest(
    @SerializedName("UserId")
    var UserId: String,
    @SerializedName("ToolId")
    var ToolId: String
)
