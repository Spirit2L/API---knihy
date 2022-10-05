package sk.stuba.fei.uim.oop.assignment3.lendingList.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.ListEntry;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.ListEntryRepository;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.BookIdRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LendingListService implements ILendingListService{

    @Autowired
    private LendingListRepository repository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ListEntryRepository listEntryRepository;

    @Override
    public List<LendingList> getAll() {
        return new ArrayList<>(this.repository.findAll());
    }

    @Override
    public LendingList create(){
        return this.repository.save(new LendingList());
    }

    @Override
    public LendingList getList(Long id) throws NotFoundException {
        LendingList l = repository.findLendingListByListId(id);
        if (l == null){
            throw new NotFoundException();
        }
        return l;
    }

    @Override
    public void deleteList(Long id) throws NotFoundException {
        LendingList lendingList = this.getList(id);
        if (lendingList.getLended()){
            for (Book book : lendingList.getListEntryList().stream().map(ListEntry::getBook).collect(Collectors.toList())){
                this.bookService.decreaseLendCount(book);
            }
        }
        this.repository.delete(lendingList);
    }

    @Override
    public LendingList addBookToList(Long listId, BookIdRequest bookId) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getList(listId);
        Book book = this.bookService.getBook(bookId.getId());
        if ((lendingList.getListEntryList().stream().map(ListEntry::getBook).collect(Collectors.toList()).contains(book)) || (lendingList.getLended())){
            throw new IllegalOperationException();
        }
        lendingList.getListEntryList().add(this.listEntryRepository.save(new ListEntry(book)));
        return this.repository.save(lendingList);
    }

    @Override
    public void deleteBookFromList(Long listId, BookIdRequest bookId) throws NotFoundException {
        LendingList lendingList = this.getList(listId);
        Book book = this.bookService.getBook(bookId.getId());
        for (int i = 0; i < lendingList.getListEntryList().size(); i++){
            if (lendingList.getListEntryList().get(i).getBook().equals(book)){
                lendingList.getListEntryList().remove(i);
                break;
            }
        }
        this.repository.save(lendingList);
    }

    @Override
    public void lendList(Long listId) throws NotFoundException, IllegalOperationException {
        LendingList lendingList = this.getList(listId);
        if (lendingList.getLended()){
            throw new IllegalOperationException();
        }
        lendingList.setLended(true);
        for (Book book : lendingList.getListEntryList().stream().map(ListEntry::getBook).collect(Collectors.toList())){
            this.bookService.increaseLendCount(book);
        }
        this.repository.save(lendingList);
    }
}
