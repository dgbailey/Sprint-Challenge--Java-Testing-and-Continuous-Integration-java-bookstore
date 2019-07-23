package com.lambdaschool.bookstore.controller;

import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.service.AuthorService;
import com.lambdaschool.bookstore.model.ErrorDetail;

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
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;


    @ApiOperation(value = "returns all Authors", response = Author.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0,
            size = 2) Pageable pageable)
    {
        List<Author> myAuthors = authorService.findAll(pageable);
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }


    //stretch put mapping
    @PutMapping(value = "/Author/{authorid}")
    public ResponseEntity<?> updateStudent(
            @RequestBody
                    Author updateAuthor,
            @PathVariable
                    long authorid)
    {
        authorService.update(updateAuthor, authorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
