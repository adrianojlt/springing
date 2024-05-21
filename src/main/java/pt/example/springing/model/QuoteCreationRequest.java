package pt.example.springing.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuoteCreationRequest {
    private String value;
    private LocalDateTime created;
    private LocalDateTime when;
    private List<String> tags;
    private String authorId;
}
