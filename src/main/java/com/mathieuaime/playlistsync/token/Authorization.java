package com.mathieuaime.playlistsync.token;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import java.io.IOException;

public enum Authorization {
  SPOTIFY {
    private String clientId = "4ae69060711c4dd0aab382a89ad87d79";
    private String clientSecret = "7903d498bdf0453baa2019b28406a997";

    private transient SpotifyApi spotifyApi = new SpotifyApi.Builder()
        .setClientId(clientId)
        .setClientSecret(clientSecret)
        .build();

    @Override
    public Token authorize() {
      try {
        ClientCredentials clientCredentials = spotifyApi.clientCredentials().build().execute();
        return new Token(clientCredentials.getAccessToken());
      } catch (IOException | SpotifyWebApiException e) {
        throw new RuntimeException("Authorization failed : " + e.getMessage());
      }
    }
  },
  DEEZER {
    @Override
    public Token authorize() {
     throw new UnsupportedOperationException();
    }
  };

  public abstract Token authorize();
}
