package com.mathieuaime.playlistsync;

import com.mathieuaime.playlistsync.model.Playlist;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class PlatformTest {

  private static final String PLAYLIST = "isioke";

  @Test
  public void getPlaylistsSpotify() throws Exception {
    Optional<Playlist> tracks = Platform.SPOTIFY.getPlaylist(PLAYLIST, Platform.SPOTIFY.userId);
    Assert.assertTrue(tracks.isPresent());
  }
}