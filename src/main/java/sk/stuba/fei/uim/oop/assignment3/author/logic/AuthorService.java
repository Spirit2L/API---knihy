package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private AuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return new ArrayList<>(this.repository.findAll());
    }

    @Override
    public Author create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setSurname(request.getSurname());
        return this.repository.save(author);
    }

    @Override
    public Author getAuthor(Long id) throws NotFoundException {
        Author a = repository.findAuthorById(id);
        if (a == null){
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public Author putAuthor(Long id, AuthorRequest request) throws NotFoundException {
        Author a = this.getAuthor(id);
        if (request.getName() != null){
            a.setName(request.getName());
        }
        if (request.getSurname() != null){
            a.setSurname(request.getSurname());
        }
        return this.repository.save(a);
    }

    @Override
    public void deleteAuthor(Long id) throws NotFoundException {
        Author a = this.getAuthor(id);
        this.repository.delete(a);
    }

    public Author addBook(Long id, Book book) throws NotFoundException{
        Author author = this.getAuthor(id);
        author.getBooks().add(book);
        return this.repository.save(author);
    }

    public void removeBook(Long id, Book book) throws NotFoundException{
        Author author = this.getAuthor(id);
        author.getBooks().remove(book);
        this.repository.save(author);
    }
}
