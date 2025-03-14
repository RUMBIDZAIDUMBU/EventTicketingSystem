package com.ticketing.EventTicketingSystem.repository;

import com.ticketing.EventTicketingSystem.Model.Artist;

import java.util.List;

public interface ArtistRepository extends AbstractNameRepo <Artist, String>{
    List<Artist> findByActive(Boolean active);
}
