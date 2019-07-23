package com.lambdaschool.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "authbooks")
public class AuthBooks extends Auditable implements Serializable {


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"authbooks", "hibernateLazyInitializer"})
    @JoinColumn(name = "authorid")
    private Author author;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"authbooks", "hibernateLazyInitializer"})
    @JoinColumn(name = "bookid")
    private Book book;

    public AuthBooks() {
    }

    public AuthBooks(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof AuthBooks))
        {
            return false;
        }
        AuthBooks userRoles = (AuthBooks) o;
        return getAuthor().equals(userRoles.getAuthor()) && getBook().equals(userRoles.getAuthor());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAuthor(), getBook());
    }
}
