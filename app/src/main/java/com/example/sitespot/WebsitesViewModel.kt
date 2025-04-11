package com.example.sitespot

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sitespot.data.WebsiteRepository
import com.example.sitespot.data.WebsitesState
import kotlinx.coroutines.launch

class WebsitesViewModel: ViewModel() {
    private val repository = WebsiteRepository()

    var uiState by mutableStateOf<WebsitesState>(WebsitesState.Loading)
        private set

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            uiState = try {
                val websites = repository.fetchWebsites()
                if (websites.isNotEmpty()) {
                    WebsitesState.Success(websites)
                } else {
                    WebsitesState.Error
                }
            } catch (e: Exception) {
                e.printStackTrace()
                WebsitesState.Error
            }
        }
    }
}