package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.Artist;
import com.ticketing.EventTicketingSystem.repository.ArtistRepository;
import com.ticketing.EventTicketingSystem.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service

public class ArtistServiceImp implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;


    @Override
    public Artist getByName(String name) {
        return artistRepository.findByActiveAndName(Boolean.TRUE,name);
    }

    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist get(String id) {
        return artistRepository.findById(id).get();
    }

    @Override
    public void delete(Artist artist) {
//artistRepository.delete(artist); This permanently removes the record from the database(hard delete)


    //this is soft delete not hard delete, for preserving historical data
    artist.setActive(Boolean.FALSE);
    artist.setDeleted(Boolean.TRUE);
    save(artist);
    }

    @Override
    public List<Artist> getPageable() {
        return List.of();
    }

    @Override
    public Artist save(Artist artist) {
        if (artist.getId() == null){
            artist.setDateCreated(LocalDateTime.now());
            return artistRepository.save(artist);
        } else {
            artist.setDateModified(LocalDateTime.now());
            return artistRepository.save(artist);
        }
    }

    @Override
    public Boolean checkDuplicate(Artist current, Artist old) {
        if (current.getId() == null) {
            return artistRepository.existsByNameIgnoreCaseAndActive(current.getName(), Boolean.TRUE);
        }

        old = get(current.getId());

        if (!current.getName().equalsIgnoreCase(old.getName())) {
            return artistRepository.existsByNameIgnoreCaseAndActive(current.getName(), Boolean.TRUE);
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public void update(String id, Artist artist) {
        Artist existingArtist = artistRepository.findById(id).orElse(null);
        if (existingArtist != null) {
            existingArtist.setName(artist.getName());
            existingArtist.setDescription(artist.getDescription());
            existingArtist.setDateModified(LocalDateTime.now());
            artistRepository.save(existingArtist);
        } else {
            throw new RuntimeException("Artist not found");
        }
    }

    @Override
    public List<Artist> getAllActiveArtists() {
        // getting all artist where active is set to True
        return artistRepository.findByActive(Boolean.TRUE);
    }

    @Override
    public List<Artist> getAllInactiveArtists() {
        // getting all artist where active is set to false , soft deleted
        return artistRepository.findByActive(Boolean.FALSE);
    }


}
