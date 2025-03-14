package com.ticketing.EventTicketingSystem.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "images")
public class Image extends BaseEntity {

    private String ticketId;
    private String fileName;
    private String fileType;
    private byte[] data;
    private final Date uploadDate = new Date();

    // Constructors
    public Image() {}

    public Image(String ticketId, String fileName, String fileType, byte[] data) {
        this.ticketId = ticketId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    // Getters and Setters
    public String getTicketId() { return ticketId; }
    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public byte[] getData() { return data; }
    public Date getUploadDate() { return uploadDate; }
}
