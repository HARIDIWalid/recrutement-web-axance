package fr.d2factory.libraryapp.model.member;

import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.service.Library;
import lombok.EqualsAndHashCode;

import java.security.cert.Extension;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Class Resident</b> <br>
 * <br>
 * A resident {@link Extension} {@link Member}
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@EqualsAndHashCode(callSuper = true)
public class Resident extends Member {

	/**
	 * Resident Constructor
	 * @param idMember
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param address
	 * @param state
	 * @param registrationDate
	 * @param wallet
	 */
    public Resident(Long idMember, String firstName, String lastName, LocalDate birthDate, String address, StateEnum state, LocalDateTime registrationDate, float wallet) {
        super(idMember, firstName, lastName, birthDate, address, state, registrationDate, wallet);
    }
}
