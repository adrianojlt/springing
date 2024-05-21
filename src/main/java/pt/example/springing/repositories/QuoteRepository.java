package pt.example.springing.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pt.example.springing.model.Quote;
import java.util.List;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, String> {

    @Query("{ 'tags': ?0 }")
    List<Quote> findByTag(String tag);
}
