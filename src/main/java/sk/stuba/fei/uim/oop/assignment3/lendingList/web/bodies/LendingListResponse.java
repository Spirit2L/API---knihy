package sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class LendingListResponse {

    private Long id;
    private List<BookResponse> lendingList;
    private Boolean lended;

    public LendingListResponse(LendingList ll){
        this.id = ll.getListId();
        this.lendingList = new ArrayList<>();
        this.lendingList = ll.getListEntryList().stream().map(ListEntry -> new BookResponse(ListEntry.getBook())).collect(Collectors.toList());
        this.lended = ll.getLended();
    }
}
