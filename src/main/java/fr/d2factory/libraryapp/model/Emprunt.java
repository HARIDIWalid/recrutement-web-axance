package fr.d2factory.libraryapp.model;

/**
 * <b>Class Emprunt</b> <br>
 * <br>
 * This class to manage book borrowing by members
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
import java.time.LocalDateTime;

import fr.d2factory.libraryapp.model.book.Book;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = { "book", "member" })
public class Emprunt {
	private Book book;					// Book
	private LocalDateTime dateEmprunt;  // Borrowing date;
	
	@Setter
	private LocalDateTime dateReturn;

	/**
	 * Emprunt : Add a book loan
	 * @param book
	 * @param dateEmprunt
	 */
	public Emprunt(Book book, LocalDateTime dateEmprunt) {
		this.book = book;
		this.dateEmprunt = dateEmprunt;
	}

}
