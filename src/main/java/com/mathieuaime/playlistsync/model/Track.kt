package com.mathieuaime.playlistsync.model

data class Track(val uri: String, val name: String, val artists: Set<Artist>)