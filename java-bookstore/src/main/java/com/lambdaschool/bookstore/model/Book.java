package com.lambdaschool.bookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonView(View.CoursesOnly.class)
    private long bookid;

    //    @JsonView(View.CoursesOnly.class)
    private String bookname;



    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    @Column
    private String ISBN;

    @Column
    private Integer copy;



    public Book()
    {
    }


    public Book(String bookname, List<Author> authors, String ISBN, Integer copy) {
        this.bookname = bookname;

        this.ISBN = ISBN;
        this.copy = copy;



        for (Author a: authors)
        {
            a.getBooks().add(this);
        }
        this.authors = authors;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getCopy() {
        return copy;
    }

    public void setCopy(Integer copy) {
        this.copy = copy;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
