package com.example.smartlockermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class NotificationResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("date")
    var Date: String,
    @SerializedName("tool")
    var Tool: ToolResponse
) {
    fun getParsedDate() : String {
        val ld = LocalDate.parse(Date.subSequence(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return "${ld.dayOfMonth}/${ld.monthValue}/${ld.year}"
    }
}