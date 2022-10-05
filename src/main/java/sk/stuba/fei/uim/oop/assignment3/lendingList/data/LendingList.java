package sk.stuba.fei.uim.oop.assignment3.lendingList.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class LendingList {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long listId;
    @OneToMany(orphanRemoval = true)
    private List<ListEntry> listEntryList;
    private Boolean lended;

    public LendingList(){
        this.listEntryList = new ArrayList<>();
        this.lended = false;
    }
}
