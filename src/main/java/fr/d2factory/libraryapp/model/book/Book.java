package fr.d2factory.libraryapp.model.book;

import fr.d2factory.libraryapp.exception.BookNotAvailableException;
import lombok.*;

/**
 * <b>Class Book</b> <br>
 * <br>
 * Book
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
public class Book {

    private long idBook;
    private String title;					// Title of book
    private String author;					// Author of book
    private ISBN isbn;						// International Standard Number
    private volatile int instanceNumber; 	// Number of book

    /**
     * add : increment instanceNumber
     */
    public synchronized void add() {
        instanceNumber++;
    }

    /**
     * minus : To manage the number of books remaining in the library
     * @throws BookNotAvailableException
     */
    public synchronized void minus() throws BookNotAvailableException {
        if (instanceNumber > 0) {
            instanceNumber--;
        } else {
            throw new BookNotAvailableException();
        }
    }

    /**
     * isAvailable : true if @instanceNumber > 0 else false
     * @return {@link boolean}
     */
    public boolean isAvailable() {
        return this.instanceNumber > 0;
    }
}