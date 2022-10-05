package sk.stuba.fei.uim.oop.assignment3.lendingList.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.LendingListResponse;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/list")
public class LendingListController {

    @Autowired
    private ILendingListService service;

    @GetMapping()
    public List<LendingListResponse> getAllLendingLists(){
        return this.service.getAll().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<LendingListResponse> addList(){
        return new ResponseEntity<>(new LendingListResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public LendingListResponse getLendingList(@PathVariable("id") Long id) throws NotFoundException {
        return new LendingListResponse(this.service.getList(id));
    }
    @DeleteMapping("/{id}")
    public void deleteLendingList(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException{
        this.service.deleteList(id);
    }
    @PostMapping("/{id}/add")
    public LendingListResponse addBookToLendingList(@PathVariable("id") Long listId, @RequestBody BookIdRequest bookId) throws NotFoundException, IllegalOperationException {
        return new LendingListResponse(this.service.addBookToList(listId, bookId));
    }
    @DeleteMapping("/{id}/remove")
    public void removeBookFromLendingList(@PathVariable("id") Long listId, @RequestBody BookIdRequest bookId) throws NotFoundException {
        this.service.deleteBookFromList(listId, bookId);
    }
    @GetMapping("/{id}/lend")
    public void lendLendingList(@PathVariable("id") Long listId) throws NotFoundException, IllegalOperationException{
        this.service.lendList(listId);
    }
}
