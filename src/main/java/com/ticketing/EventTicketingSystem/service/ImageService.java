package com.ticketing.EventTicketingSystem.service;

import com.ticketing.EventTicketingSystem.Model.Image;
import io.jsonwebtoken.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService extends GenericService<Image> {
    Page<Image> getPageable(Pageable pageable);

    Image saveImage(MultipartFile file) throws IOException, java.io.IOException;


    Image findImageByTicketId(String ticketId);

    Image saveImage(MultipartFile file, String ticketId) throws java.io.IOException;
}
