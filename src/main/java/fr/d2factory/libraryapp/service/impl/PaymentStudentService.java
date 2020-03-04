package fr.d2factory.libraryapp.service.impl;

import fr.d2factory.libraryapp.commons.MyUtils;
import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.member.Student;
import fr.d2factory.libraryapp.service.Payment;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * <b>Class PaymentStudentService</b> <br>
 * <br>
 * The student payment service
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
public class PaymentStudentService implements Payment<Student> {

    @Override
    public void payBook(Student student, Emprunt emprunt, LocalDateTime datePaymentOrReturn) {
        long nbDays = Duration.between(emprunt.getDateEmprunt(), datePaymentOrReturn).toDays();

        Float cout = 0f;

        if (student.isPremiereAnnee()) {
            if (nbDays > 15) {
                // la difference au dela de 15 J
                cout = (nbDays - 15) * MyUtils.priceStudentInferior15Days;
            }
        } else {
            cout = nbDays * MyUtils.priceStudentInferior15Days;
        }

        if (nbDays > 30) {
            student.setState(StateEnum.ENRETARD);
        }

        student.setWallet(student.getWallet() - cout);
        emprunt.setDateReturn(datePaymentOrReturn);
    }

}
