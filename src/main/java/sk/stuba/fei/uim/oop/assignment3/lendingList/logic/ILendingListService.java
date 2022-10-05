package sk.stuba.fei.uim.oop.assignment3.lendingList.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.BookIdRequest;

import java.util.List;

public interface ILendingListService {
    List<LendingList> getAll();
    LendingList create();
    LendingList getList(Long id) throws NotFoundException;
    void deleteList(Long id) throws NotFoundException, IllegalOperationException;
    LendingList addBookToList(Long listId, BookIdRequest bookId) throws NotFoundException, IllegalOperationException;
    void deleteBookFromList(Long listId, BookIdRequest bookId) throws NotFoundException;
    void lendList(Long listId) throws NotFoundException, IllegalOperationException;
}
