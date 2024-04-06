package pt.example.springing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.example.springing.model.Author;
import pt.example.springing.model.Quote;

import java.util.List;

@RestController
@RequestMapping(value = "/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Quote>> quotes() {
        return ResponseEntity.ok().body(quoteService.getQuotes());
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok().body(quoteService.createAuthor(author));
    }
}
