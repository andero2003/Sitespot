package com.example.sitespot.data

sealed interface WebsitesState {
    data class Success(val websites: List<Website>): WebsitesState
    data object Error : WebsitesState
    data object Loading : WebsitesState
}