package com.example.smartlockermobile.ui.notifications

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.smartlockermobile.data.SessionManager
import com.example.smartlockermobile.data.network.ApiClient
import com.example.smartlockermobile.data.network.ApiStatus
import com.example.smartlockermobile.data.network.dto.responses.NotificationResponse
import com.example.smartlockermobile.data.network.dto.responses.ToolResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {
    private val _notifications = MutableLiveData<List<NotificationResponse>?>()
    val notifications: MutableLiveData<List<NotificationResponse>?> = _notifications

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private var apiClient: ApiClient
    private var sessionManager: SessionManager
    private var _token: String

    init {
        _notifications.value = null
        apiClient = ApiClient()
        sessionManager = SessionManager(getApplication())
        _token = sessionManager.fetchAuthToken()!!

        getNotifications()
    }

    fun globalGetNotifications() {
        getNotifications()
    }

    private fun getNotifications() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val apiClient = ApiClient()

            try {
                _notifications.value = apiClient.getApiService().getNotifications(sessionManager.fetchUserId(),"Bearer $_token")
                _status.value = ApiStatus.DONE
                Log.i("API", "Procedure: GET Appointments Value: ${_notifications.value}")
            } catch (e: Exception) {
                Log.i("API", "Procedure: GET Appointments Error: $e")
                _status.value = ApiStatus.ERROR
                _notifications.value = ArrayList()
            }
        }
    }

//    fun saveToolIdToSP(id: String) {
//        sessionManager.saveToolId(id)
//    }
}