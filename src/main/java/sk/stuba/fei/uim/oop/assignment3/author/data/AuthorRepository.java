package sk.stuba.fei.uim.oop.assignment3.author.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Override
    List<Author> findAll();
    Author findAuthorById(Long id);
}
