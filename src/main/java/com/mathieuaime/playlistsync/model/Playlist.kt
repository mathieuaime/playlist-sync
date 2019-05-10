package com.mathieuaime.playlistsync.model

data class Playlist(val id: String, val name: String, val tracks: Set<Track>)