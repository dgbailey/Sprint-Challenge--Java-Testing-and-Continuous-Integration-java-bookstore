package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//import com.lambdaschool.bookstore.view.CountStudentsInCourses;

public interface BookRepository extends CrudRepository<Book, Long>
{
    ArrayList<Book> findCoursesByBooknameEquals(String name);

    Book findCourseByBookname(String name);

    Book findByBookid(long bookid);

    @Modifying
    @Query(value = "DELETE FROM authbooks WHERE bookid = :bookid", nativeQuery = true)
    void deleteBookFromAuthbooks(long bookid);



//    @Query(value = "SELECT s.courseid, coursename, count(studid) as countstudents FROM studcourses s INNER JOIN course c on s.courseid=c.courseid GROUP BY s.courseid, coursename", nativeQuery = true)
//    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();
}

