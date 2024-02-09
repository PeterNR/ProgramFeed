package com.example.alternativuniversnrk

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentFeed(state: LiveItemsState, onClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Direkte") })
        }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = it,
        ) {
            if (state.isLoading) {
                item {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Card(
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterHorizontally),
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "Laster..",
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            } else {
                state.items.forEach { liveItem ->
                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            when (liveItem.liveType) {
                                "channel" -> ImageCard(liveItem = liveItem, onClick)
                                "event" -> EventCard(liveItem, onClick = onClick)
                                "program" -> ImageCard(liveItem = liveItem, onClick)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ContentFeedPreview() {
// Mock ProgramDetail instances
    val mockCurrentlyPlaying = ProgramDetail(
        time = "21:05", // ISO 8601 format
        programName = "Daily News"
    )

    val mockUpNext = ProgramDetail(
        time = "21:05", // ISO 8601 format
        programName = "Tech Trends"
    )

// Mock LiveItem instances
    val mockLiveItem1 = LiveItem(
        liveType = "channel",
        uri = "https://stream.example.com/news",
        imageUrl = "https://fastly.picsum.photos/id/365/300/200.jpg?hmac=_ZkyfESsHt2RteInYatkFqQ4-OAG2em4hYhUoIJYbD0",
        channelId = "news-1",
        currentlyPlaying = mockCurrentlyPlaying,
        upNext = mockUpNext
    )

    val mockLiveItem2 = LiveItem(
        liveType = "event",
        uri = "https://stream.example.com/tech",
        imageUrl = "https://example.com/images/tech.jpg",
        channelId = "tech-2",
        currentlyPlaying = mockCurrentlyPlaying,
        upNext = mockUpNext
    )
    val mockLiveItem3 = LiveItem(
        liveType = "program",
        uri = "https://stream.example.com/tech",
        imageUrl = "https://fastly.picsum.photos/id/358/500/300.jpg?hmac=RogC8P-1BznDwjManDeiFlMHkMCctaIcs3cxH7RTivA",
        channelId = null,
        currentlyPlaying = null,
        upNext = null
    )

// Mock LiveResponse instance
    val mockLiveResponse = LiveResponse(
        liveItems = listOf(mockLiveItem1, mockLiveItem2, mockLiveItem3)
    )

// Mock LiveItemsState instance for a loaded state
    val mockLiveItemsStateLoaded = LiveItemsState(
        isLoading = false,
        items = listOf(mockLiveItem1, mockLiveItem2, mockLiveItem3)
    )

// Mock LiveItemsState instance for a loading state
    val mockLiveItemsStateLoading = LiveItemsState(
        isLoading = true,
        items = emptyList() // or null if you prefer to showcase a loading state without any items
    )

    ContentFeed(state = (mockLiveItemsStateLoaded), { _ -> Unit })
//    ContentFeed(state = (mockLiveItemsStateLoading), { _ -> Unit })
}