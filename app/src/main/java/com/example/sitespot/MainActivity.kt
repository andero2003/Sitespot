package com.example.sitespot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sitespot.data.WebsitesState
import com.example.sitespot.ui.theme.SitespotTheme
import com.example.sitespot.widgets.Center
import com.example.sitespot.widgets.ErrorScreen
import com.example.sitespot.widgets.WebsiteList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SitespotTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val websitesViewModel: WebsitesViewModel = viewModel()
                    MainScreen(
                        websitesViewModel.uiState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(state: WebsitesState, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(12.dp)
    ) {
        Column {
            Text(
                text = "Popular Websites",
                fontWeight = FontWeight.Black,
                fontSize = 30.sp,
                modifier = modifier.padding(bottom = 4.dp)
            )
            HorizontalDivider()
            when (state) {
                is WebsitesState.Loading -> Center{ CircularProgressIndicator() }
                is WebsitesState.Error -> Center{ ErrorScreen() }
                is WebsitesState.Success -> WebsiteList(state.websites)
            }
        }
    }
}