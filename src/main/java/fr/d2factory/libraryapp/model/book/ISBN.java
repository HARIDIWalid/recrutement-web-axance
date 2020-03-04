package fr.d2factory.libraryapp.model.book;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * <b>Class Book</b> <br>
 * <br>
 * Book
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@Getter
@EqualsAndHashCode(of = "isbnCode")
public class ISBN {
    long isbnCode; // Code Isbn

    /**
     * ISBN : Get {@link Long} isbnCode
     * @param isbnCode
     */
    public ISBN(long isbnCode) {
        this.isbnCode = isbnCode;
    }
}
