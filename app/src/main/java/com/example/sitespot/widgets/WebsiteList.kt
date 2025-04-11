package com.example.sitespot.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sitespot.data.Website

enum class SortOrder { NONE, ASC, DESC }

@Composable
fun WebsiteList(
    websites: List<Website>,
    modifier: Modifier = Modifier
) {
    var sortOrder by remember { mutableStateOf(SortOrder.NONE) }

    // perform sorting in this widget to encapsulate this local UI logic
    val sortedWebsites = remember(websites, sortOrder) {
        when (sortOrder) {
            SortOrder.ASC -> websites.sortedBy { it.name }
            SortOrder.DESC -> websites.sortedByDescending { it.name }
            SortOrder.NONE -> websites
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Button(onClick = {
            sortOrder = when (sortOrder) {
                SortOrder.NONE -> SortOrder.ASC
                SortOrder.ASC -> SortOrder.DESC
                SortOrder.DESC -> SortOrder.NONE
            }
        }) {
            Text(
                text = when (sortOrder) {
                    SortOrder.ASC -> "Sort ↑"
                    SortOrder.DESC -> "Sort ↓"
                    else -> "Sort"
                }
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .padding(vertical = 6.dp)
            .verticalScroll(rememberScrollState())
    ) {
        sortedWebsites.forEach { website ->
            WebsiteCard(website)
        }
    }
}