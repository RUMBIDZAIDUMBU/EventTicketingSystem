package com.ticketing.EventTicketingSystem.repository;

import com.ticketing.EventTicketingSystem.Model.Image;

import java.util.List;

public interface ImageRepository extends AbstractRepo <Image, String>{
    List<Image> findByTicketId(String ticketId);
    Image findByTicketIdAndActive(String ticketId,Boolean active);
}
