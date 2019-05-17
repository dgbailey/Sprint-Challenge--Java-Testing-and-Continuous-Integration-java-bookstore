package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.model.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>
{
    List<Author> findByAuthornameContainingIgnoreCase(String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO authbooks(authorid, bookid) values (:authorid, :bookid)", nativeQuery = true)
    void insertIntoAuthBooks(long authorid, long bookid);

}
