package com.bestbook.persistence;

import com.bestbook.domain.model.BestBook;
import com.bestbook.domain.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestBookRepository extends MongoRepository<BestBook,String> {
    public BestBook findBestBookByItem(Item item);
}
