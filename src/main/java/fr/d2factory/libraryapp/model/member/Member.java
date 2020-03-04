package fr.d2factory.libraryapp.model.member;

import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.service.Library;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <b>Class Member</b> <br>
 * <br>
 * A member is a person who can borrow and return books to a {@link Library}
 * A member can be either a student or a resident
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "idMember")
public abstract class Member {


    protected Long idMember;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String address;
    @Setter
    protected StateEnum state;
    protected LocalDateTime registrationDate;
    /**
     * An initial sum of money the member has
     */
    protected float wallet;

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }
}
