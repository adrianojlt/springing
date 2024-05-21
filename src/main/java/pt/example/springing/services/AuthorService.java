package pt.example.springing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pt.example.springing.model.Author;
import pt.example.springing.model.AuthorCreationRequest;
import pt.example.springing.repositories.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public boolean IsAuthorPresent(String id) {
        return this.authorRepository.findById(id).isPresent();
    }

    public Author createAuthor(AuthorCreationRequest authorRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(authorRequest, author);
        return authorRepository.save(author);
    }
}
