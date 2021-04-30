package com.example.smartlockermobile.data.network.dto.responses

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class ServiceBookResponse(
    @SerializedName("id")
    var Id: String,
    @SerializedName("lastServiceDate")
    var LastServiceDate: String,
    @SerializedName("msBetweenServices")
    var MsBetweenServices: Long,
    @SerializedName("maxUsages")
    var MaxUsages: Int,
    @SerializedName("usages")
    var Usages: Int
) {
    fun getDate() : String {
        val ld = LocalDate.parse(LastServiceDate.subSequence(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return "${ld.dayOfMonth}/${ld.monthValue}/${ld.year}"
    }
}