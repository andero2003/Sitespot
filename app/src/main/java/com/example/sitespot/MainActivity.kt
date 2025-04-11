package com.example.sitespot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            SitespotTheme( darkTheme = isDarkTheme ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val websitesViewModel: WebsitesViewModel = viewModel()
                    MainScreen(
                        websitesViewModel.uiState,
                        modifier = Modifier.padding(innerPadding),
                        onToggleTheme = { isDarkTheme = !isDarkTheme },
                        isDarkTheme = isDarkTheme
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    state: WebsitesState,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .padding(12.dp)
    ) {
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            )  {
                Text(
                    text = "Sitespot",
                    fontWeight = FontWeight.Black,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Button(onClick = onToggleTheme ) {
                    Text(if (isDarkTheme) "Light Mode" else "Dark Mode")
                }
            }
            HorizontalDivider()
            when (state) {
                is WebsitesState.Loading -> Center{ CircularProgressIndicator() }
                is WebsitesState.Error -> Center{ ErrorScreen() }
                is WebsitesState.Success -> WebsiteList(state.websites)
            }
        }
    }
}