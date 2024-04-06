package pt.example.springing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Author")
public class Author {
    @Id
    private String id;
    private String name;
    private LocalDateTime birthday;
    private LocalDateTime death;
}
