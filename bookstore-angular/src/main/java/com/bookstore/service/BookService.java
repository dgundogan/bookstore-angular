package com.bookstore.service;

import com.bookstore.domain.Book;

import java.util.List;

/**
 * Created by DG on 9/10/17.
 */
public interface BookService {

    List<Book> findAll();

    Book findOne(Long id);

    Book save(Book book);

    List<Book> blurrySearch(String title);

    void removeOne(Long id);
}
