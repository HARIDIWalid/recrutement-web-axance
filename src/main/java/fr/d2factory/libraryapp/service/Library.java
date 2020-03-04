package fr.d2factory.libraryapp.service;

import fr.d2factory.libraryapp.exception.BookNotAvailableException;
import fr.d2factory.libraryapp.exception.HasLateBooksException;
import fr.d2factory.libraryapp.exception.IsbnNotAvailableException;
import fr.d2factory.libraryapp.exception.ParamException;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.member.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The library class is in charge of stocking the books and managing the return delays and members
 *
 * The books are available via the {@link fr.d2factory.libraryapp.repository.BookRepository}
 */
public interface Library {

    /**
     * A member is borrowing a book from our library.
     *
     * @param isbnCode the isbn code of the book
     * @param member the member who is borrowing the book
     *
     * @return the book the member wishes to obtain if found
     * @throws HasLateBooksException in case the member has books that are late
     * @throws BookNotAvailableException 
     * @throws ParamException 
     * @throws IsbnNotAvailableException 
     *
     * @see fr.d2factory.libraryapp.model.book.ISBN
     * @see Member
     */
    Book borrowBook(long isbnCode, Member member) throws HasLateBooksException, ParamException, BookNotAvailableException, IsbnNotAvailableException;

    /**
     * A member returns a book to the library.
     * We should calculate the tarif and probably charge the member
     *
     * @param book the {@link Book} they return
     * @param member the {@link Member} who is returning the book
     *
     * @see Member#payBook(int)
     */
    boolean returnBook(Book book, Member member, LocalDateTime dateEmprunt);
}
