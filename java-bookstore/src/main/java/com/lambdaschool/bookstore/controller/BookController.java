package com.lambdaschool.bookstore.controller;



import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.service.AuthorService;
import com.lambdaschool.bookstore.model.ErrorDetail;

import com.lambdaschool.bookstore.service.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Autowired
    private BookService bookService;


    @ApiOperation(value = "returns all Authors", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0,
            size = 2) Pageable pageable)
    {
        List<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

    //stretch delete mapping
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<?> deleteBookById(@PathVariable long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

