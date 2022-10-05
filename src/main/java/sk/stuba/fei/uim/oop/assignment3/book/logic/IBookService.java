package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookEditRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();
    Book create(BookRequest request) throws NotFoundException;
    Book getBook(Long id) throws NotFoundException;
    Book putBook(Long id, BookEditRequest request) throws NotFoundException;
    void deleteBook(Long id) throws NotFoundException;
    Amount getBookAmount(Long id) throws NotFoundException;
    Amount postBookAmount(Long id, Amount amount) throws NotFoundException;
    Amount getBookLendCount(Long id) throws NotFoundException;
}
