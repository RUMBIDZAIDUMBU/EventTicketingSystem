package com.ticketing.EventTicketingSystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
abstract public class BaseEntity implements Serializable {

    @Id
    private String id;
    private String createdBy;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateModified;
    @Version
    private Long version;
    private Boolean active = Boolean.TRUE;
    private Boolean deleted = Boolean.FALSE;



    public BaseEntity(String id) {
        this.id = id;
    }

}
