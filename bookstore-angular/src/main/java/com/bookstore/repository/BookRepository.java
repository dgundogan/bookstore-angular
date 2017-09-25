package com.bookstore.repository;

import com.bookstore.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by DG on 9/10/17.
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitleContaining(String keyword);
}
