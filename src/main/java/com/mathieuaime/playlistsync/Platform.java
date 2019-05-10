package com.mathieuaime.playlistsync;

import com.mathieuaime.playlistsync.mapper.PlaylistMapper;
import com.mathieuaime.playlistsync.mapper.TrackMapper;
import com.mathieuaime.playlistsync.model.Playlist;
import com.mathieuaime.playlistsync.model.Track;
import com.mathieuaime.playlistsync.token.Authorization;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public enum Platform {
  SPOTIFY {
    private String userId = "APRJJCm-SFuRkEbI_oeUFw APRJJCm-SFuRkEbI_oeUFw";

    @Override
    Optional<Playlist> getPlaylist(String playlistName) {
      SpotifyApi spotifyApi = getSpotifyApi();

      try {
        Paging<PlaylistSimplified> playlistsPage =
            spotifyApi.getListOfUsersPlaylists(userId).build().execute();

        return Arrays.stream(playlistsPage.getItems())
            .filter(playlist -> playlist.getName().equals(playlistName))
            .map(this::retrieveTracks)
            .findFirst();
      } catch (IOException | SpotifyWebApiException e) {
        throw new RuntimeException("Error getting playlist : " + e.getMessage());
      }
    }

    private Playlist retrieveTracks(PlaylistSimplified playlistSimplified) {
      SpotifyApi spotifyApi = getSpotifyApi();

      try {
        Paging<PlaylistTrack> playlistTracks = spotifyApi
            .getPlaylistsTracks(playlistSimplified.getId())
            .build().execute();
        Set<Track> tracks = Arrays.stream(playlistTracks.getItems())
            .map(TrackMapper::toTrack)
            .collect(Collectors.toSet());
        return PlaylistMapper.toPlaylist(playlistSimplified, tracks);
      } catch (SpotifyWebApiException | IOException e) {
        throw new RuntimeException("Error getting playlist : " + e.getMessage());
      }
    }

    private SpotifyApi getSpotifyApi() {
      String accessToken = Authorization.SPOTIFY.authorize().getAccessToken();
      return new SpotifyApi.Builder().setAccessToken(accessToken).build();
    }
  },
  DEEZER {
    @Override
    Optional<Playlist> getPlaylist(String playlistName) {
      throw new UnsupportedOperationException();
    }
  };

  abstract Optional<Playlist> getPlaylist(String playlistName);
}
