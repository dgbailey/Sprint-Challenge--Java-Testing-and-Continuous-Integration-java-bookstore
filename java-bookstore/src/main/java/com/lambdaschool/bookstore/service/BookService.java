package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.model.Book;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;

public interface BookService
{
    List<Book> findAll(Pageable pageable);

//    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();

    void delete(long id);

//    Course findCourseByCourseName (String course);

    Book update(Book author, long id);

    Book findByBookid(long bookid);
}
