package pt.example.springing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Quote")
public class Quote {
    @Id
    private String id;
    private String value;
    private LocalDateTime created;
    private LocalDateTime when;
}
