package com.ticketing.EventTicketingSystem.service;

import com.ticketing.EventTicketingSystem.Model.Artist;

import java.util.List;

public interface ArtistService extends GenericNameService <Artist> {
    void update(String id, Artist artist);

    List<Artist> getAllActiveArtists();
    List<Artist> getAllInactiveArtists();
}
