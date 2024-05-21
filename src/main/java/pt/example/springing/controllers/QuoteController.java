package pt.example.springing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.example.springing.services.AuthorService;
import pt.example.springing.services.QuoteService;
import pt.example.springing.model.Quote;
import pt.example.springing.model.QuoteCreationRequest;

import java.util.List;

@RestController
@RequestMapping(value = "/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;
    private final AuthorService authorService;

    @Autowired
    public QuoteController(QuoteService quoteService, AuthorService authorService) {
        this.quoteService = quoteService;
        this.authorService = authorService;
    }

    @GetMapping({"/","/quotes"})
    public ResponseEntity<List<Quote>> quotes() {
        return ResponseEntity.ok().body(quoteService.getQuotes());
    }

    @PostMapping("/quote")
    public ResponseEntity<?> createQuote(@RequestBody QuoteCreationRequest quote) {

        if (quote.getAuthorId() == null || !this.authorService.IsAuthorPresent(quote.getAuthorId())) {
            return new ResponseEntity<>("An Author must be provided!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(quoteService.createQuote(quote));
    }

    @PutMapping("/quote/{id}")
    public ResponseEntity<?> updateQuote(@PathVariable String id, @RequestBody QuoteCreationRequest quote) {

        if (!this.quoteService.IsQuotePresent(id)) {
            return new ResponseEntity<>("Quote not Found!!!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(quoteService.updateQuote(id, quote));
    }

    @DeleteMapping("/quote/{id}/tag/{tag}")
    public ResponseEntity<?> deleteTag(@PathVariable String id, @PathVariable String tag) {

        if (this.quoteService.IsQuotePresent(id)) {
            return new ResponseEntity<>("Quote not Found!!!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(quoteService.deleteTag(id, tag));
    }

    @PutMapping("/quote/{id}/tag/{tag}")
    public ResponseEntity<?> addTag(@PathVariable String id, @PathVariable String tag) {

        if (this.quoteService.IsQuotePresent(id)) {
            return new ResponseEntity<>("Quote not Found!!!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(quoteService.addTag(id, tag));
    }
}
