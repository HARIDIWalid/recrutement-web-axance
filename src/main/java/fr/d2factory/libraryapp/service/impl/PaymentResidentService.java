package fr.d2factory.libraryapp.service.impl;

import fr.d2factory.libraryapp.commons.MyUtils;
import fr.d2factory.libraryapp.commons.StateEnum;
import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.member.Resident;
import fr.d2factory.libraryapp.service.Payment;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * <b>Class PaymentResidentService</b> <br>
 * <br>
 * The resident payment service
 * 
 * @author Walid Haridi (Sopra HR Software)
 */
public class PaymentResidentService implements Payment<Resident> {

    @Override
    public void payBook(Resident resident, Emprunt emprunt, LocalDateTime datePaymentOrReturn) {
        long nbDays = Duration.between(emprunt.getDateEmprunt(), datePaymentOrReturn).toDays();

        Float cout ;
        if (nbDays <= 60) {
            cout = nbDays * MyUtils.priceResident10;
        } else {
            cout = (60 * MyUtils.priceResident10) + ((nbDays - 60) * MyUtils.priceResident20);
            resident.setState(StateEnum.ENRETARD);
        }

        resident.setWallet(resident.getWallet() - cout);
        emprunt.setDateReturn(datePaymentOrReturn);
    }

}
