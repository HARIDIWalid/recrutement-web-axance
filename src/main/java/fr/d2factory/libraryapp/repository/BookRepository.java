package fr.d2factory.libraryapp.repository;

import java.security.cert.Extension;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.omg.IOP.ExceptionDetailMessage;

import fr.d2factory.libraryapp.exception.ParamException;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.book.ISBN;
import fr.d2factory.libraryapp.model.member.Member;

/**
 * <b>Class BookRepository</b> <br>
 * <br>
 * The book repository emulates a database via 2 HashMaps
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
public class BookRepository {
	private Map<ISBN, Book> availableBooks = new HashMap<>();  		// HashMap for available books
	private Map<Book, LocalDate> borrowedBooks = new HashMap<>();	// HashMap for borrowed books
	private static BookRepository bookRepository = null;			// Book repository

	/**
	 * BookRepository : for simulate data
	 * Design patern singleton
	 * @return {@link fr.d2factory.libraryapp.book.BookRepository}
	 */
	public static BookRepository getInstance() {
		if (bookRepository == null) {
			bookRepository = new BookRepository();
		}
		return bookRepository;
	}

	/**
	 * addBooks : for add book
	 * @param books
	 */
	public void addBooks(List<Book> books) {
		if (books != null) {
			books.forEach(book -> availableBooks.put(book.getIsbn(), book));
		}
	}

	/**
	 * findBook : for searsh a book by isbn
	 * @param isbnCode
	 * @return {@link Optional}
	 */
	public Optional<Book> findBook(long isbnCode) {
		Book book = availableBooks.get(new ISBN(isbnCode));
		return  book != null ? Optional.of(book) : Optional.empty();
	}

	/*
	public void saveBookBorrow(Book book, LocalDate borrowedAt) {
		borrowedBooks.put(book, borrowedAt);
	}

	public LocalDate findBorrowedBookDate(Book book) {
		// TODO implement the missing feature
		return borrowedBooks.get(book);
	}
	*/
}
