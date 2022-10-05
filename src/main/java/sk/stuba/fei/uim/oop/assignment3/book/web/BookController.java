package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookEditRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService service;


    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return this.service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }
    
    @PostMapping()
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) throws NotFoundException{
        return new ResponseEntity<>(new BookResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") Long id) throws NotFoundException {
        return new BookResponse(this.service.getBook(id));
    }

    @PutMapping("/{id}")
    public BookResponse putBook(@PathVariable("id") Long id, @RequestBody BookEditRequest request) throws NotFoundException {
        return new BookResponse(this.service.putBook(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteBook(id);
    }

    @GetMapping("/{id}/amount")
    public Amount getBookAmount(@PathVariable("id") Long id) throws NotFoundException {
        return this.service.getBookAmount(id);
    }

    @PostMapping("/{id}/amount")
    public Amount postBookAmount(@PathVariable("id") Long id, @RequestBody Amount amount) throws NotFoundException {
        return this.service.postBookAmount(id, amount);
    }

    @GetMapping("/{id}/lendCount")
    public Amount getBookLendCount(@PathVariable("id") Long id) throws NotFoundException {
        return this.service.getBookLendCount(id);
    }
}
