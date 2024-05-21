package pt.example.springing.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "Author")
public class Author {
    @Id
    private String id;
    private String name;
    private LocalDateTime birthday;
    private LocalDateTime death;
}
