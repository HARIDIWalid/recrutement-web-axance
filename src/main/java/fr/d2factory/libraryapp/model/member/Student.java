package fr.d2factory.libraryapp.model.member;

import fr.d2factory.libraryapp.commons.StateEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.security.cert.Extension;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Class Student</b> <br>
 * <br>
 * A Student {@link Extension} {@link Member}
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class Student extends Member {

    private boolean premiereAnnee;		// If student is in first year

    /**
     * Student Constuctor
     * @param idMember
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param address
     * @param state
     * @param registrationDate
     * @param wallet
     * @param premiereAnnee
     */
    public Student(Long idMember, String firstName, String lastName, LocalDate birthDate, String address, StateEnum state, LocalDateTime registrationDate, float wallet, boolean premiereAnnee) {
        super(idMember, firstName, lastName, birthDate, address, state, registrationDate, wallet);
        this.premiereAnnee = premiereAnnee;
    }


}
