package sk.stuba.fei.uim.oop.assignment3.author.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService service;

    @GetMapping()
    public List<AuthorResponse> getAllAuthors(){
        return this.service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest request){
        return new ResponseEntity<>(new AuthorResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable("id") Long id) throws NotFoundException {
        return new AuthorResponse(this.service.getAuthor(id));
    }

    @PutMapping("/{id}")
    public AuthorResponse putAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequest request) throws NotFoundException {
        return new AuthorResponse(this.service.putAuthor(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteAuthor(id);
    }
}
