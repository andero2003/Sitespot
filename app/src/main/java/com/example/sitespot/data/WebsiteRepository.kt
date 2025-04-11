package com.example.sitespot.data

class WebsiteRepository {
    suspend fun fetchWebsites(): List<Website> {
        return try {
            ApiClient.websiteApi.getWebsitesList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
