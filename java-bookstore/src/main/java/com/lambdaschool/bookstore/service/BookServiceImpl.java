package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import com.lambdaschool.bookstore.repository.BookRepository;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        ArrayList<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findByBookid(long bookid) {

            return bookRepository.findById(bookid).orElseThrow(() -> new EntityNotFoundException(Long.toString(bookid)));
    }



//
//    @Override
//    public ArrayList<CountStudentsInCourses> getCountStudentsInCourse()
//    {
//        return courserepos.getCountStudentsInCourse();
//    }

    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteBookFromAuthbooks(id);
            bookRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }


    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (currentBook.getBookname() != null)
        {
            currentBook.setBookname(book.getBookname());
        }

        if (currentBook.getCopy() != null)
        {
            currentBook.setCopy(book.getCopy());
        }

        if (currentBook.getISBN() != null)
        {
            currentBook.setISBN(book.getISBN());
        }

        return bookRepository.save(currentBook);
    }
}


