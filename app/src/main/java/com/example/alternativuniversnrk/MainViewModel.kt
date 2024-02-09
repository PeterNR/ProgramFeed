package com.example.alternativuniversnrk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var _state by mutableStateOf(LiveItemsState())

    init {
        fetchLiveItems()
    }

    private fun fetchLiveItems() {
        viewModelScope.launch {
            _state = _state.copy(isLoading = true)

            val newItems = try {
                ApiClient.apiService.getLiveItems()
            }catch ( e:Exception){
                println("handle this error in some way")
                LiveResponse(emptyList())
            }

            _state = _state.copy(
                isLoading = false,
                items = newItems.liveItems
            )
        }
    }
}

data class LiveItemsState(
    val isLoading: Boolean = false,
    val items: List<LiveItem> = emptyList(),
)