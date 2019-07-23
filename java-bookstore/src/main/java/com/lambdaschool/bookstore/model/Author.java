package com.lambdaschool.bookstore.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String authorname;

    @ManyToMany
    @JoinTable(name = "authbooks",
            joinColumns = {@JoinColumn(name = "authorid")},
            inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    @Column
    String firstname;

    @Column
    String lastname;


    public Author()
    {
    }



    public Author(String authorname, String firstname, String lastname, List<Book> books)
    {
        this.authorname = authorname;
        for (Book b: books)
        {
            b.getAuthors().add(this);
        }
        this.books = books;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

