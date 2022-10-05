package sk.stuba.fei.uim.oop.assignment3.lendingList.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingListRepository extends CrudRepository<LendingList, Long> {

    @Override
    List<LendingList> findAll();
    LendingList findLendingListByListId(Long id);
}
