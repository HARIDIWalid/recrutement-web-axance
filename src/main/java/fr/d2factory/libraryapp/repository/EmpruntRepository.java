package fr.d2factory.libraryapp.repository;

import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.member.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <b>Class EmpruntRepository</b> <br>
 * <br>
 * The emprunt repository simulate a database via 2 HashMaps
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmpruntRepository {
    private static Map<Member, List<Emprunt>> emprunts = new HashMap<>();
    private static EmpruntRepository empruntRepository = null;

    /**
	 * EmpruntRepository : for simulate data
	 * @return {@link EmpruntRepository}
	 */
    public static EmpruntRepository getInstance() {
        if (empruntRepository == null) {
            empruntRepository = new EmpruntRepository();
        }
        return empruntRepository;
    }

    /**
     * add : for borrow book
     * @param member
     * @param emprunt
     */
    public void add(Member member, Emprunt emprunt) {
        List<Emprunt> data = emprunts.get(member);

        if (data == null) {
            data = new ArrayList<>();
        }

        data.add(emprunt);
        emprunts.put(member, data);
    }
    /**
     * returnEmprunt for return book
     * @param member
     * @param book
     * @param returnDate
     * @return {@link Boolean}
     */
    public boolean returnEmprunt(Member member, Book book, LocalDateTime returnDate) {
        emprunts.get(member).stream().filter(x -> x.getBook().equals(book)).findFirst().map(mapper -> {
            mapper.setDateReturn(returnDate);
            return mapper;
        });

        return true;
    }

    /**
     * findAllEmprunts : to find all the loans
     * @param member
     * @return {@link List} of {@link Emprunt}
     */
    public List<Emprunt> findAllEmprunts(Member member) {
        List<Emprunt> data = emprunts.get(member);
        return data == null ? new ArrayList<>() : (List<Emprunt>) Collections.unmodifiableCollection(data);
    }

    /**
     * find : find Emprunt by book and member
     * @param member
     * @param book
     * @return {@link Optional} {@link Emprunt}
     */
    public Optional<Emprunt> find(Member member, Book book) {
        return emprunts.get(member).stream().filter(x -> x.getBook().equals(book) && x.getDateReturn() == null)
                .findFirst();
    }

}
