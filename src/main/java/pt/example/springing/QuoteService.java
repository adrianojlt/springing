package pt.example.springing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.example.springing.model.Author;
import pt.example.springing.model.Quote;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class QuoteService {
    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    public QuoteService(AuthorRepository authorRepository, QuoteRepository quoteRepository) {
        this.authorRepository = authorRepository;
        this.quoteRepository = quoteRepository;
    }

    public List<Author> getAuthors() {
       return authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }
}
