package com.mathieuaime.playlistsync.mapper;

import com.mathieuaime.playlistsync.model.Artist;
import com.mathieuaime.playlistsync.model.Track;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class TrackMapper {

  private TrackMapper() {
    throw new AssertionError();
  }

  public static Track toTrack(PlaylistTrack playlistTrack) {
    com.wrapper.spotify.model_objects.specification.Track track = playlistTrack.getTrack();
    Set<Artist> artists = Arrays.stream(track.getArtists()).map(ArtistMapper::toArtist)
        .collect(Collectors.toSet());
    return new Track(track.getUri(), track.getName(), artists);
  }
}
