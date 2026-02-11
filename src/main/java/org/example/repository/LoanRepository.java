package org.example.repository;


import org.example.domain.Loan;
import org.example.ojects.BookId;
import org.example.ojects.LoanId;
import org.example.ojects.MemberId;

import java.util.List;

public interface LoanRepository {

    void save(Loan loan);

    Loan findById(LoanId id);

    boolean isBookLoaned(BookId bookId);

    List<Loan> activeLoansForMember(MemberId memberId);

    List<Loan> findAll();
}
