package com.musicsync.backend.spotify;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public record SpotifyUserProfile (
    @JsonProperty("id")
    String id,

    @JsonProperty("display_name")
    String displayName
){

}
