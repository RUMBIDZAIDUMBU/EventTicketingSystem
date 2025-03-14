package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.Image;
import com.ticketing.EventTicketingSystem.repository.ImageRepository;
import com.ticketing.EventTicketingSystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        return List.of(); // Empty list, implement logic if needed
    }

    @Override
    public Image get(String id) {
        Optional<Image> image = imageRepository.findById(id); // Find image by ID
        return image.orElse(null); // Return null if not found
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image); // Delete the image entity
    }

    @Override
    public List<Image> getPageable() {
        return List.of(); // Empty list, implement logic if needed
    }

    @Override
    public Page<Image> getPageable(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public Image saveImage(MultipartFile file) throws io.jsonwebtoken.io.IOException, IOException {
        return null;
    }

    @Override
    public Image findImageByTicketId(String ticketId) {
        return imageRepository.findByTicketIdAndActive(ticketId,Boolean.TRUE);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image); // Save the image entity
    }

    @Override
    public Boolean checkDuplicate(Image current, Image old) {
        return current.equals(old); // Check if the current and old images are identical
    }

    @Override
    public Image saveImage(MultipartFile file, String ticketId) throws IOException {
        // Convert the file into an Image object and save it in MongoDB
        Image image = new Image(
                ticketId,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes() // Convert file content to a byte array
        );
        return imageRepository.save(image); // Save the image in MongoDB
    }
}
