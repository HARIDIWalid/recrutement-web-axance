package fr.d2factory.libraryapp.service;

import java.time.LocalDateTime;

import fr.d2factory.libraryapp.model.Emprunt;
import fr.d2factory.libraryapp.model.book.Book;
import fr.d2factory.libraryapp.model.member.Member;

public interface Payment <T extends Member>{
	void payBook(T t, Emprunt emprunt ,LocalDateTime datePaymentOrReturn);
}
