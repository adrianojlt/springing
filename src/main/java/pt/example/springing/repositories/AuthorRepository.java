package pt.example.springing.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.example.springing.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
