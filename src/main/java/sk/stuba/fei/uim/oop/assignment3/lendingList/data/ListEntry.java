package sk.stuba.fei.uim.oop.assignment3.lendingList.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ListEntry {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book book;

    public ListEntry(Book book){
        this.book = book;
    }
}
