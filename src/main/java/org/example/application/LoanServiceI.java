package org.example.application;

import org.example.domain.Loan;
import org.example.entity.Book;
import org.example.ojects.BookId;
import org.example.ojects.LoanId;
import org.example.ojects.MemberId;

import java.util.List;

public interface LoanServiceI {



    void borrowBook(BookId bookId, MemberId memberId, LoanId loanId);

    void returnBook(LoanId loanId);

    List<Loan> overdueLoans();

}
