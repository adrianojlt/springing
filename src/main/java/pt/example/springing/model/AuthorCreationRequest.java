package pt.example.springing.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthorCreationRequest {
    private String name;
    private LocalDateTime birthday;
    private LocalDateTime death;
}
