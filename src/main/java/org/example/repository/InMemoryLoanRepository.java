package org.example.repository;

import org.example.domain.Loan;
import org.example.exception.LoanNotFoundException;
import org.example.ojects.BookId;
import org.example.ojects.LoanId;
import org.example.ojects.MemberId;


import java.util.ArrayList;
import java.util.List;

public class InMemoryLoanRepository implements LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    @Override
    public void save(Loan loan) {
        loans.add(loan);
    }

    @Override
    public Loan findById(LoanId id) {
        return loans.stream()
                .filter(l -> l.getLoanId().equals(id))
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));
    }

    @Override
    public boolean isBookLoaned(BookId bookId) {
        return loans.stream()
                .anyMatch(l -> l.getBookId().equals(bookId) && l.isActive());
    }

    @Override
    public List<Loan> activeLoansForMember(MemberId memberId) {
        return loans.stream()
                .filter(l -> l.getMemberId().equals(memberId))
                .filter(Loan::isActive)
                .toList();
    }

    @Override
    public List<Loan> findAll() {
        return List.copyOf(loans);
    }
}
