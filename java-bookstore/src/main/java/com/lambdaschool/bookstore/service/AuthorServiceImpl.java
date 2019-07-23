package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authrepos;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> findAll(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public void insertIntoAuthBooks(long authorid, long bookid) {
        Author addAuthor = this.findAuthorById(authorid);
        Book addToBook= bookRepository.findByBookid(bookid);
        addToBook.getAuthors().add(addAuthor);
//        bookRepository.save(addToBook);
        authrepos.insertIntoAuthBooks(authorid, bookid);

    }

    @Override
    public List<Author> findAuthorByNameLike(String name, Pageable pageable) {
        List<Author> list = new ArrayList<>();
        authrepos.findByAuthornameContainingIgnoreCase(name,pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author findAuthorById(long id) throws EntityNotFoundException
    {
        return authrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }



    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (authrepos.findById(id).isPresent())
        {
            authrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Author save(Author author)
    {
        Author newAuthor = new Author();
        newAuthor.setAuthorname(author.getAuthorname());

        return authrepos.save(newAuthor);
    }

    @Override
    public Author update(Author author, long id)
    {
        Author currentAuthor = authrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (author.getAuthorname() != null)
        {
            currentAuthor.setAuthorname(author.getAuthorname());
        }

        return authrepos.save(currentAuthor);
    }
}
