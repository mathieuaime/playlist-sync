package com.mathieuaime.playlistsync.mapper;

import com.mathieuaime.playlistsync.model.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;

public final class ArtistMapper {

  private ArtistMapper() {
    throw new AssertionError();
  }

  public static Artist toArtist(ArtistSimplified artistSimplified) {
    return new Artist(artistSimplified.getId(), artistSimplified.getName());
  }
}
