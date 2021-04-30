package com.example.smartlockermobile.ui.tools

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.smartlockermobile.data.SessionManager
import com.example.smartlockermobile.data.network.ApiClient
import com.example.smartlockermobile.data.network.ApiStatus
import com.example.smartlockermobile.data.network.dto.responses.ToolResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class ToolsViewModel(application: Application) : AndroidViewModel(application) {
    private val _tools = MutableLiveData<List<ToolResponse>?>()
    val tools: MutableLiveData<List<ToolResponse>?> = _tools

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private var apiClient: ApiClient
    private var sessionManager: SessionManager
    private var _token: String

    init {
        _tools.value = null
        apiClient = ApiClient()
        sessionManager = SessionManager(getApplication())
        _token = sessionManager.fetchAuthToken()!!

        getTools()
    }

    fun globalGetTools() {
        getTools()
    }

    private fun getTools() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val apiClient = ApiClient()

            try {
                _tools.value = apiClient.getApiService().getTools("Bearer $_token")
                _status.value = ApiStatus.DONE
                Log.i("API", "Procedure: GET Appointments Value: ${_tools.value}")
            } catch (e: Exception) {
                Log.i("API", "Procedure: GET Appointments Error: $e")
                _status.value = ApiStatus.ERROR
                _tools.value = ArrayList()
            }
        }
    }

    fun saveToolIdToSP(id: String) {
        sessionManager.saveToolId(id)
    }
}