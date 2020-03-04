package fr.d2factory.libraryapp.library.service;

import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.book.ISBN;
import fr.d2factory.libraryapp.model.member.Student;
import fr.d2factory.libraryapp.service.impl.PaymentStudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PaymentStudentServiceTest {

    private PaymentStudentService paymentStudentService = new PaymentStudentService();
    private LocalDateTime localDateTimeMars04 = LocalDateTime.now();

    @Test
    public void payBook__Sup1ereAnnee() {

        //Given
        Book book = new Book();
        book.setIdBook(99L);
        book.setIsbn(new ISBN(10099L));

        Emprunt emprunt = new Emprunt(book, localDateTimeMars04.minusDays(4));
        Student student = new Student(0l, "", "", null, "", null, localDateTimeMars04, 10, false);


        LocalDateTime datePaymentOrReturn = LocalDateTime.now();

        paymentStudentService.payBook(student, emprunt, datePaymentOrReturn);

        Assertions.assertNotNull(emprunt.getDateReturn());
        Assertions.assertEquals(datePaymentOrReturn, emprunt.getDateReturn());
        Assertions.assertEquals(new Float("9.6"), student.getWallet());
        Assertions.assertFalse(StateEnum.ENRETARD.equals(student.getState()));
    }


    @Test
    public void payBook__1ereAnneeEtInf15() {

        //Given
        Book book = new Book();
        book.setIdBook(99L);
        book.setIsbn(new ISBN(10099L));

        Emprunt emprunt = new Emprunt(book, localDateTimeMars04.minusDays(4));
        Student student = new Student(0l, "", "", null, "", null, localDateTimeMars04, 10, true);

        LocalDateTime datePaymentOrReturn = localDateTimeMars04;

        paymentStudentService.payBook(student, emprunt, datePaymentOrReturn);

        Assertions.assertNotNull(emprunt.getDateReturn());
        Assertions.assertEquals(datePaymentOrReturn, emprunt.getDateReturn());
        Assertions.assertEquals(10, student.getWallet());
        Assertions.assertFalse(StateEnum.ENRETARD.equals(student.getState()));
    }

    @Test
    public void payBook__1ereAnneeEtSup30() {

        //Given
        Book book = new Book();
        book.setIdBook(99L);
        book.setIsbn(new ISBN(10099L));

        Emprunt emprunt = new Emprunt(book, localDateTimeMars04.minusDays(33));
        Student student = new Student(0l, "", "", null, "", null, localDateTimeMars04, 10, true);

        LocalDateTime datePaymentOrReturn = localDateTimeMars04;

        paymentStudentService.payBook(student, emprunt, datePaymentOrReturn);

        Assertions.assertNotNull(emprunt.getDateReturn());
        Assertions.assertEquals(datePaymentOrReturn, emprunt.getDateReturn());
        Assertions.assertEquals(new Float("8.2"), student.getWallet());
        Assertions.assertTrue(StateEnum.ENRETARD.equals(student.getState()));
    }
}
