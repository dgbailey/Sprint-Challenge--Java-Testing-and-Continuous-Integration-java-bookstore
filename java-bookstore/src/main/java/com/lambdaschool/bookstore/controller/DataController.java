package com.lambdaschool.bookstore.controller;

import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.service.AuthorService;
import com.lambdaschool.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @PostMapping(value = "/author/{authorid}/book/{bookid}")
    public ResponseEntity<?> addUserRole(@PathVariable long authorid, @PathVariable long bookid)
    {
        authorService.insertIntoAuthBooks(authorid, bookid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/data/books/{bookid}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @PathVariable
                    long bookid)
    {
        bookService.update(updateBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
