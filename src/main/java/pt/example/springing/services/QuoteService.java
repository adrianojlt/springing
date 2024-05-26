package pt.example.springing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pt.example.springing.model.Author;
import pt.example.springing.model.Quote;
import pt.example.springing.model.QuoteCreationRequest;
import pt.example.springing.repositories.AuthorRepository;
import pt.example.springing.repositories.QuoteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    public boolean IsQuotePresent(String id) {
        return this.quoteRepository.findById(id).isPresent();
    }

    public Quote createQuote(QuoteCreationRequest quoteRequest) {

        Optional<Author> author = authorRepository.findById(quoteRequest.getAuthorId());

        if (author.isEmpty()) {
           throw new RuntimeException("Author not found");
        }

        Quote quote = new Quote();
        BeanUtils.copyProperties(quoteRequest, quote);
        quote.setCreated(LocalDateTime.now());
        quote.setAuthor(author.get());

        return quoteRepository.save(quote);
    }

    public Quote updateQuote(String id, QuoteCreationRequest quoteRequest) {
        Quote quote = new Quote();
        BeanUtils.copyProperties(quoteRequest, quote);
        quote.setId(id);
        return quoteRepository.save(quote);
    }

    public Quote deleteTag(String quoteId, String tag) {

        Optional<Quote> quote = quoteRepository.findById(quoteId);

        if (quote.isEmpty()) {
            throw new RuntimeException("Quote not found");
        }

        quote.get().getTags().remove(tag);
        quoteRepository.save(quote.get());
        return quote.get();
    }

    public Quote addTag(String quoteId, String tag) {

        Optional<Quote> quote = quoteRepository.findById(quoteId);

        if (quote.isEmpty()) {
            throw new RuntimeException("Quote not found");
        }

        quote.get().getTags().add(tag);
        quoteRepository.save(quote.get());
        return quote.get();
    }
}
