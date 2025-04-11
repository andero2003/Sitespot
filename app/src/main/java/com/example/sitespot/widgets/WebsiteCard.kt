package com.example.sitespot.widgets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sitespot.data.Website
import androidx.core.net.toUri


@Composable
fun WebsiteCard(website: Website, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .shadow(4.dp)
        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        .clickable {
            val intent = Intent(Intent.ACTION_VIEW, website.url.toUri())
            context.startActivity(intent)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SvgCompatibleImage(
                url = website.icon,
                contentDescription = website.name,
                modifier = modifier
                    .size(72.dp)
                    .padding(12.dp)
            )
            Column() {
                Text(
                    website.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = modifier
                        .padding(4.dp)
                        .padding(top = 2.dp, start=4.dp, end=4.dp)
                )
                Text(
                    website.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    modifier = modifier
                        .padding(4.dp)
                        .padding(start=4.dp, end=4.dp)
                )
            }
        }
    }
}