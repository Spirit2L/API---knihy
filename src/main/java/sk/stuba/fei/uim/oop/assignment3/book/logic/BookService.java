package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.logic.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookEditRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private BookRepository repository;
    @Autowired
    private AuthorService authorService;

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(this.repository.findAll());
    }

    @Override
    public Book create(BookRequest request) throws NotFoundException{
        Book book = new Book();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setAuthor(request.getAuthor());
        book.setPages(request.getPages());
        book.setAmount(request.getAmount());
        book.setLendCount(request.getLendCount());
        book = this.repository.save(book);
        this.authorService.addBook(request.getAuthor(), book);
        return book;
    }

    @Override
    public Book getBook(Long id) throws NotFoundException {
        Book b = repository.findBookById(id);
        if (b == null){
            throw new NotFoundException();
        }
        return b;
    }

    @Override
    public Book putBook(Long id, BookEditRequest request) throws NotFoundException{
        Book b = this.getBook(id);
        if (request.getName() != null){
            b.setName(request.getName());
        }
        if (request.getDescription() != null){
            b.setDescription(request.getDescription());
        }
        if ((request.getAuthor() != null) && (request.getAuthor() != 0)){
            b.setAuthor(request.getAuthor());
        }
        if ((request.getPages() != null) && (request.getPages() != 0)){
            b.setPages(request.getPages());
        }
        return this.repository.save(b);
    }

    @Override
    public void deleteBook(Long id) throws NotFoundException {
        Book b = this.getBook(id);
        this.authorService.removeBook(b.getAuthor(), b);
        this.repository.deleteById(id);
    }

    @Override
    public Amount getBookAmount(Long id) throws NotFoundException {
        Book b = this.getBook(id);
        return new Amount(b.getAmount());
    }

    @Override
    public Amount postBookAmount(Long id, Amount amount) throws NotFoundException {
        Book b = this.getBook(id);
        b.setAmount((int)(b.getAmount() + amount.getAmount()));
        repository.save(b);
        return new Amount(b.getAmount());
    }

    @Override
    public Amount getBookLendCount(Long id) throws NotFoundException {
        Book b = this.getBook(id);
        return new Amount(b.getLendCount());
    }

    public Book increaseLendCount(Book book){
        book.setLendCount(book.getLendCount() + 1);
        return repository.save(book);
    }

    public Book decreaseLendCount(Book book){
        book.setLendCount(book.getLendCount() - 1);
        return repository.save(book);
    }
}
