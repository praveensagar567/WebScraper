package com.scraper.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "partners")
public class ResponseDTO {

    @Id
    private String id;
    private String title;
    private String description;
    private String iconUrl;
}
