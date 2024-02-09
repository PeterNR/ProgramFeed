package com.example.alternativuniversnrk

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ImageCard(liveItem: LiveItem, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clickable { onClick(liveItem.uri) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                model = liveItem.imageUrl,
                contentDescription = "bilde ${liveItem.currentlyPlaying?.programName}",
            )
            if (liveItem.channelId != null) {
                ChannelInfo(liveItem)
            } else {
                ProgramInfo()
            }
        }
    }
}

@Composable
fun ChannelInfo(liveItem: LiveItem) {
    Column(Modifier.padding(10.dp)) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = liveItem.channelId!!,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Row {
            Text(
                text = liveItem.currentlyPlaying!!.time,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = liveItem.currentlyPlaying.programName,
                modifier = Modifier.weight(4f),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Row {
            Text(
                text = liveItem.upNext!!.time,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = liveItem.upNext.programName,
                modifier = Modifier.weight(4f),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
fun ProgramInfo() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Card(shape = RoundedCornerShape(5.dp)) {
            Text(
                text = "Direkte nå",
                modifier = Modifier
                    .background(Color.Red)
                    .padding(5.dp),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
    Row(Modifier.fillMaxSize()) {
        val bgColor = Color(254, 0, 0, 120)
        LinearProgressIndicator(
            progress = 0.2f,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .align(Alignment.Bottom),
            trackColor = bgColor,
            color = bgColor,
        )
    }
}