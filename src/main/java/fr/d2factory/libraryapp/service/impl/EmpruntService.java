package fr.d2factory.libraryapp.service.impl;

import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.exception.BookNotAvailableException;
import fr.d2factory.libraryapp.exception.HasLateBooksException;
import fr.d2factory.libraryapp.exception.IsbnNotAvailableException;
import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.member.Member;
import fr.d2factory.libraryapp.repository.BookRepository;
import fr.d2factory.libraryapp.repository.EmpruntRepository;
import fr.d2factory.libraryapp.service.Library;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <b>Class EmpruntService</b> <br>
 * <br>
 * The emprunt service
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@NoArgsConstructor
public class EmpruntService implements Library {

    private EmpruntRepository empruntRepository;
    private BookRepository bookRepository;

    /**
     * EmpruntService
     * @param empruntRepository
     * @param bookRepository
     */
    public EmpruntService(EmpruntRepository empruntRepository, BookRepository bookRepository) {
        this.empruntRepository = empruntRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book borrowBook(long isbnCode, Member member) throws IsbnNotAvailableException, BookNotAvailableException {

        if (StateEnum.ENRETARD.equals(member.getState())) {
            throw new HasLateBooksException("");
        }

        Book book = bookRepository.findBook(isbnCode).orElseThrow(() -> new IsbnNotAvailableException());

        if (book.isAvailable()) {
            validateBorrow(member, book);
            return book;
        } else {
            throw new BookNotAvailableException();
        }

    }

    @Override
    public boolean returnBook(Book book, Member member, LocalDateTime dateEmprunt) {
        empruntRepository.returnEmprunt(member, book, dateEmprunt);
        bookRepository.findBook(book.getIsbn().getIsbnCode()).ifPresent(
                book1 -> {
                    book1.add();
                }
        );

        return true;
    }

    /**
     * validateBorrow : for validate a borrow
     * @param member
     * @param book
     * @throws BookNotAvailableException
     */
    private synchronized void validateBorrow(Member member, Book book) throws BookNotAvailableException {
        book.minus();
        Emprunt emprunt = new Emprunt(book, LocalDateTime.now());
        empruntRepository.add(member, emprunt);
    }

}
