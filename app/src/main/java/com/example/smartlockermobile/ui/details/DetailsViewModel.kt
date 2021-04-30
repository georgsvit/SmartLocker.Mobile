package com.example.smartlockermobile.ui.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.smartlockermobile.data.SessionManager
import com.example.smartlockermobile.data.network.ApiClient
import com.example.smartlockermobile.data.network.ApiStatus
import com.example.smartlockermobile.data.network.dto.requests.QueueRegisterRequest
import com.example.smartlockermobile.data.network.dto.responses.LockerResponse
import com.example.smartlockermobile.data.network.dto.responses.ServiceBookResponse
import com.example.smartlockermobile.data.network.dto.responses.ToolResponse
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedTool = MutableLiveData<ToolResponse>()
    var selectedTool: LiveData<ToolResponse> = _selectedTool

    private val _selectedToolId = MutableLiveData<String>()
    var selectedToolId: LiveData<String> = _selectedToolId

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _queueResult = MutableLiveData<Boolean>()
    val queueResult: LiveData<Boolean> = _queueResult

    private var sessionManager: SessionManager
    private var _token: String

    init {
        sessionManager = SessionManager(getApplication())
        _token = sessionManager.fetchAuthToken()!!

        _selectedToolId.value = sessionManager.fetchToolId()

        if (_selectedToolId.value != "-1") {
            getTool(_selectedToolId.value!!)
        }
    }

    private fun getTool(id: String) {
        viewModelScope.launch {
            val apiClient = ApiClient()

            try {
                _selectedTool.value = apiClient.getApiService().getToolById(id, "Bearer $_token")
                Log.i("API", "Procedure: GET Tool Value: ${_selectedTool.value}")
            } catch (e: java.lang.Exception) {
                Log.i("API", "Procedure: GET Tool Error: $e")
                _selectedTool.value = ToolResponse("-1", "None", "None", "None", -1, "None", ServiceBookResponse("-1", "", -1, -1, -1), LockerResponse("-1", ""))
            }
        }
    }

    fun enterQueue() {
        viewModelScope.launch {
            val apiClient = ApiClient()
            val userId = sessionManager.fetchUserId()
            try {
                _queueResult.value = apiClient.getApiService().enterQueue(QueueRegisterRequest(userId, _selectedToolId.value!!), "Bearer $_token")
                Log.i("API", "Procedure: POST QueueRequest Value: ${_queueResult.value}")
            } catch (e: java.lang.Exception) {
                Log.i("API", "Procedure: POST QueueRequest Error: $e")
                _queueResult.value = false
            }
        }
    }
}