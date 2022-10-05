package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

@Getter
@Setter
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private Long author;
    private Integer pages;
    private Integer amount;
    private Integer lendCount;

    public BookResponse(Book b){
        this.id = b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        this.author = b.getAuthor();
        this.pages = b.getPages();
        this.amount = b.getAmount();
        this.lendCount = b.getLendCount();
    }
}
