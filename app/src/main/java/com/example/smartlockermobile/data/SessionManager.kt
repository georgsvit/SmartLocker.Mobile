package com.example.smartlockermobile.data

import android.content.Context
import android.content.SharedPreferences
import com.example.smartlockermobile.R
import com.example.smartlockermobile.data.network.dto.responses.JWTokenResponse
import java.text.SimpleDateFormat
import java.util.*

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"
        const val USER_SURNAME = "user_surname"
        const val USER_ROLE = "user_role"
        const val TOKEN_DATE = "token_date"
        const val USER_ID = "user_id"
    }

    fun saveAllData(tokenResponse: JWTokenResponse) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, tokenResponse.AccessToken)
        editor.putString(USER_NAME, tokenResponse.Name)
        editor.putString(USER_SURNAME, tokenResponse.Surname)
        editor.putString(USER_ROLE, tokenResponse.RoleName)
        editor.putString(USER_ID, tokenResponse.Id)

        editor.putString(TOKEN_DATE, tokenResponse.ExpiresAt.toString())
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchUserRole(): String {
        return prefs.getString(USER_ROLE, null).toString()
    }

    fun fetchDate(dateName: String): Date? {
        val formatter = SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH)
        val dateInString = prefs.getString(dateName, null)
        return if (dateInString != null) formatter.parse(dateInString) else null
    }
}