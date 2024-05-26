package pt.example.springing.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Document(collection = "Quote")
@CompoundIndex(name = "tags_index", def = "{ 'tags': 1 }") // improve query performance
public class Quote {

    @Id
    private String id;

    private String value;
    private LocalDateTime created;
    private LocalDateTime when;
    private Set<String> tags;

    @DBRef
    private Author author;
}
