package com.mathieuaime.playlistsync.mapper;

import com.mathieuaime.playlistsync.model.Playlist;
import com.mathieuaime.playlistsync.model.Track;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import java.util.Set;

public final class PlaylistMapper {

  private PlaylistMapper() {
    throw new AssertionError();
  }

  public static Playlist toPlaylist(PlaylistSimplified playlistSimplified, Set<Track> tracks) {
    return new Playlist(playlistSimplified.getId(), playlistSimplified.getName(), tracks);
  }
}
