package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{
    List<Author> findAll(Pageable pageable);

    Author findAuthorById(long id);

    List<Author> findAuthorByNameLike(String name, Pageable pageable);

    void delete(long id);

    Author save(Author author);

    Author update(Author author, long id);

    void insertIntoAuthBooks(long authorid, long bookid);
}
