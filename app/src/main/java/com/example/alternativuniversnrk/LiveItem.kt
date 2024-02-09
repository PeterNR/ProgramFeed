package com.example.alternativuniversnrk

data class  LiveResponse(
    val liveItems: List<LiveItem>
)
data class LiveItem(
    val liveType: String,
    val uri: String,
    val imageUrl: String?,
    val channelId: String?,
    val currentlyPlaying: ProgramDetail?,
    val upNext: ProgramDetail?
)

data class ProgramDetail(
    val time: String,
    val programName: String
)