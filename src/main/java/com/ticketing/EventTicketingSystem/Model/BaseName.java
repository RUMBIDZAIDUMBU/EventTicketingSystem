package com.ticketing.EventTicketingSystem.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
abstract public class BaseName extends BaseEntity {
    private String name;
    private String description;

    public BaseName(String id) {
        super(id);
    }

}
