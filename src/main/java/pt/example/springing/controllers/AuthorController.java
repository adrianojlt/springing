package pt.example.springing.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.example.springing.model.Author;
import pt.example.springing.model.AuthorCreationRequest;
import pt.example.springing.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/quotes")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> authors() {
        return ResponseEntity.ok().body(authorService.getAuthors());
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest author) {
        return ResponseEntity.ok().body(authorService.createAuthor(author));
    }
}
