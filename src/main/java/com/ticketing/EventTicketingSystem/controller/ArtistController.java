package com.ticketing.EventTicketingSystem.controller;
import com.ticketing.EventTicketingSystem.Model.Artist;
import com.ticketing.EventTicketingSystem.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> saveArtist(@RequestBody Artist artist){
        Map<String, Object> response = new HashMap<>();

        try{

            if (!artistService.checkDuplicate(artist,artist)){
                Artist newArtist = artistService.save(artist);
                response.put("Artist Saved",newArtist);
            }else{
                response.put("Duplicate",true);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all/artist")
    public ResponseEntity<?> getAllArtist(){
        List<Artist> artistList = artistService.getAll();
        return new ResponseEntity<>(artistList, HttpStatus.OK);
    }

    // getting  all artist where active is set to True
     @GetMapping("/get-active-artist")
        public ResponseEntity<?> getAllActiveArtist(){
            List<Artist> artistList = artistService.getAllActiveArtists();
            return new ResponseEntity<>(artistList, HttpStatus.OK);
        }

    // getting  all artist where active is set to false, soft deleted
    @GetMapping("/get-inactive-artist")
    public ResponseEntity<?> getAllInActiveArtist(){
        List<Artist> artistList = artistService.getAllInactiveArtists();
        return new ResponseEntity<>(artistList, HttpStatus.OK);
    }

    //path variable
    @GetMapping("/get-artist/{name}")
    public ResponseEntity<?> getArtist(@PathVariable("name") String name){

        Artist artist = artistService.getByName(name);

        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    //request parameter
    @GetMapping("/get-artist")
    public ResponseEntity<?> getArtistByName(@RequestParam("name") String name){
        Artist artist = artistService.getByName(name);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }
    @GetMapping("/get-artist-by-id")
    public ResponseEntity<?> getArtistById(@RequestParam("id") String id){
        Artist artist = artistService.get(id);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteArtist(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Artist artist = artistService.get(id);
            if (artist == null) {
                response.put("message", "Artist not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            artistService.delete(artist);
            response.put("message", "Artist deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            response.put("message", "System error occurred while deleting artist.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateArtist(@PathVariable String id, @RequestBody Artist artist) {
        artistService.update(id, artist);
        return new ResponseEntity<>("Artist updated successfully", HttpStatus.OK);
    }


}
