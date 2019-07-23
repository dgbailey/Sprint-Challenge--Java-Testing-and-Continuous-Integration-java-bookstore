package com.lambdaschool.bookstore;

import com.lambdaschool.bookstore.model.*;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import com.lambdaschool.bookstore.repository.RoleRepository;
import com.lambdaschool.bookstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    AuthorRepository authorRepository;
    BookRepository bookRepository;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, AuthorRepository authorRepository,BookRepository bookRepository)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = rolerepos.save(r1);
        r2 = rolerepos.save(r2);
        r3 = rolerepos.save(r3);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> data = new ArrayList<>();
        data.add(new UserRoles(new User(), r2));
        data.add(new UserRoles(new User(), r3));


        User u1 = new User("barnbarn", "ILuvM4th!", users);

        User u2 = new User("admin", "password", admins);

        User u3 = new User("cinnamon", "123456", data);

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);


//        Author a1 = new Instructor("Sally");

//        i1.getCourses().add(c1);
//        Instructor i2 = new Instructor("Lucy");
//        i2.setInstructid(2);
//        Instructor i3 = new Instructor("Charlie");
//        i3.setInstructid(3);

//        instructrepos.save(i1);
//        instructrepos.save(i2);
//        instructrepos.save(i3);

        //you cannot do auditing with a SQL file.  Dates  and other fields
        // for AUDIT logs will come up as NULL.
        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
//        ArrayList<Course> coursesStudCourses = new ArrayList<>();
        ArrayList<Author> authors2 = new ArrayList<>();
        ArrayList<Book> books2 = new ArrayList<>();

//seems like studcourse construtor needs to be used in order to have audit fields populate

        Book b1 = new Book("Data Science",authors,"ISBN234",1999);
        Author a1 = new Author("John Edwards", "John", "Edwards",books);
        Book b2 = new Book("Surviving Java",authors2,"ISBN235",2019);
        Author a2 = new Author("Dustin Bailey", "Dustin", "Bailey",books2);




        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        b2.getAuthors().add(a2);
        a2.getBooks().add(b2);



//        StudCourses auditStudCourses = new StudCourses(s1,c1);

        authorRepository.save(a1);
        bookRepository.save(b1);
        authorRepository.save(a2);
        bookRepository.save(b2);
    }
}
