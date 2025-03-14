package com.ticketing.EventTicketingSystem.controller;

import com.ticketing.EventTicketingSystem.Model.Image;
import com.ticketing.EventTicketingSystem.repository.ImageRepository;
import com.ticketing.EventTicketingSystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService service;

    // Upload Image with Ticket ID
    @PostMapping("/upload/{ticketId}")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file,
                                             @PathVariable("ticketId") String ticketId) {
        try {
            // Create a new Image object from the file and ticket ID
            Image image = new Image(ticketId, file.getOriginalFilename(), file.getContentType(), file.getBytes());

            // Save the image in the repository
            imageRepository.save(image);

            // Return the uploaded image with HTTP status CREATED (201)
            return ResponseEntity.status(HttpStatus.CREATED).body(image);
        } catch (IOException e) {
            // If an error occurs during the upload, return an error message with BAD_REQUEST (400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Get Images by Ticket ID
    @GetMapping("/images/{ticketId}")
    public ResponseEntity<List<Image>> getImagesByTicketId(@PathVariable String ticketId) {
        List<Image> images = imageRepository.findByTicketId(ticketId);
        if (images.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(images); // No images found
        }
        return ResponseEntity.ok(images); // Return list of images if found
    }

    // Get Image by ID
//    @GetMapping("/get-image")
//    public Image getImage(@RequestParam("id") String id) {
//        return service.findImageByTicketId(id);
//    }

    @GetMapping("/get-image")
    public ResponseEntity<byte[]> getImage(@RequestParam("ticketId") String ticketId) {
        // Assume this method retrieves the stored binary data (from database, filesystem, etc.)
        Image image = service.findImageByTicketId(ticketId);

        byte[] imageData = image.getData();

        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Change to IMAGE_PNG if needed
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

}
