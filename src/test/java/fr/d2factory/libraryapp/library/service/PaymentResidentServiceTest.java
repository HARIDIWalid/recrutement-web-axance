package fr.d2factory.libraryapp.library.service;

import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.book.ISBN;
import fr.d2factory.libraryapp.model.member.Resident;
import fr.d2factory.libraryapp.service.impl.PaymentResidentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PaymentResidentServiceTest {

    private PaymentResidentService paymentResidentService = new PaymentResidentService();
    private LocalDateTime localDateTimeMars03 = LocalDateTime.now();
    
    @Test
    public void payBook__Inf60() {

        //Given
        Book book = new Book();
        book.setIdBook(99L);
        book.setIsbn(new ISBN(10099L));

        Emprunt emprunt = new Emprunt(book, localDateTimeMars03.minusDays(3));
        Resident resident = new Resident(0l, "", "", null, "", null, null, 10);


        LocalDateTime datePaymentOrReturn = LocalDateTime.now();

        paymentResidentService.payBook(resident, emprunt, datePaymentOrReturn);

        Assertions.assertNotNull(emprunt.getDateReturn());
        Assertions.assertEquals(datePaymentOrReturn, emprunt.getDateReturn());
        Assertions.assertEquals(new Float("9.7"), resident.getWallet());
        Assertions.assertFalse(StateEnum.ENRETARD.equals(resident.getState()));
    }

    @Test
    public void payBook__Sup60() {

        //Given
        Book book = new Book();
        book.setIdBook(99L);
        book.setIsbn(new ISBN(10099L));

        Emprunt emprunt = new Emprunt(book, localDateTimeMars03.minusDays(63));
        Resident resident = new Resident(0l, "", "", null, "", null, localDateTimeMars03, 10);

        LocalDateTime datePaymentOrReturn = LocalDateTime.now();

        paymentResidentService.payBook(resident, emprunt, datePaymentOrReturn);

        Assertions.assertNotNull(emprunt.getDateReturn());
        Assertions.assertEquals(datePaymentOrReturn, emprunt.getDateReturn());
        Assertions.assertEquals(new Float("3.4"), resident.getWallet());
        Assertions.assertTrue(StateEnum.ENRETARD.equals(resident.getState()));
    }

}
