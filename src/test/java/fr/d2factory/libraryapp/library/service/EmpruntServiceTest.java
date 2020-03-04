package fr.d2factory.libraryapp.library.service;

import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.exception.BookNotAvailableException;
import fr.d2factory.libraryapp.exception.HasLateBooksException;
import fr.d2factory.libraryapp.exception.IsbnNotAvailableException;
import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.book.ISBN;
import fr.d2factory.libraryapp.model.member.Member;
import fr.d2factory.libraryapp.model.member.Student;
import fr.d2factory.libraryapp.repository.BookRepository;
import fr.d2factory.libraryapp.repository.EmpruntRepository;
import fr.d2factory.libraryapp.service.impl.EmpruntService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmpruntServiceTest {

    private EmpruntService empruntService;
    private long isbnCode;
    private Member member;

    @BeforeEach
    void setup() {
        BookRepository bookRepository = BookRepository.getInstance();
        List<Book> books = new ArrayList<>();
        books.add(new Book(99L, "LIvre ID 99", "Author", new ISBN(10099L), 0));
        books.add(new Book(98L, "LIvre ID 98", "Author", new ISBN(10098L), 1));
        books.add(new Book(97L, "LIvre ID 97", "Author", new ISBN(10097L), 10));

        bookRepository.addBooks(books);

        EmpruntRepository empruntRepository = EmpruntRepository.getInstance();

        empruntService = new EmpruntService(empruntRepository, bookRepository);

        member = new Student(0l, "", "", null, "", null, LocalDateTime.now(), 10, false);
    }

    @Test
    public void borrowBook__HasLateBooksException() throws Exception {
        member.setState(StateEnum.ENRETARD);
        try {
            empruntService.borrowBook(1l, member);
            Assertions.fail("An exception must be thrown : Has late books");
        } catch (Throwable th) {
            Assertions.assertTrue(th instanceof HasLateBooksException);
        }
    }

    @Test
    public void borrowBook__IsbnNotAvailableException() throws Exception {
        member.setState(StateEnum.ACTIF);
        try {
            empruntService.borrowBook(1l, member);
            Assertions.fail("An exception must be thrown : Isbn not available");
        } catch (Throwable th) {
            Assertions.assertTrue(th instanceof IsbnNotAvailableException);
        }
    }

    @Test
    public void borrowBook__BookNotAvailableException() throws Exception {
        member.setState(StateEnum.ACTIF);
        try {
            empruntService.borrowBook(10099L, member);
            Assertions.fail("An exception must be thrown : Book not available");
        } catch (Throwable th) {
            Assertions.assertTrue(th instanceof BookNotAvailableException);
        }
    }

    @Test
    public void borrowBook() throws Exception {
        member.setState(StateEnum.ACTIF);


        //1er emprunt du livre, il passse
        Book book1 = empruntService.borrowBook(10098L, member);
        Assertions.assertEquals(0, book1.getInstanceNumber());
        try {
            //2eme emprunt, is down!
            empruntService.borrowBook(10098L, member);
            Assertions.fail("An exception must be thrown : same borrow");
        } catch (Throwable th) {
            System.out.println(th.getStackTrace());
            Assertions.assertTrue(th instanceof BookNotAvailableException);
        }

        for (int i = 10; i > 0; i--) {
            Book book = empruntService.borrowBook(10097L, member);
            Assertions.assertEquals(i - 1, book.getInstanceNumber());
        }

    }

    @Test
    public void returnBook() {
        BookRepository bookRepository = BookRepository.getInstance();
        EmpruntRepository empruntRepository = EmpruntRepository.getInstance();
        Book book = new Book();
        book.setIdBook(99L);
        book.setIsbn(new ISBN(10099L));

        Emprunt emprunt = new Emprunt(book, LocalDateTime.now());
        empruntRepository.add(member, emprunt);

        empruntService = new EmpruntService(empruntRepository, bookRepository);

        Assertions.assertTrue(empruntService.returnBook(book, member, LocalDateTime.now()));
    }

}
