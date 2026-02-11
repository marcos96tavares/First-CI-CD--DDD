package org.example.application;

import org.example.domain.Loan;
import org.example.exception.BookAlreadyLoanException;
import org.example.exception.LoanLimitExceededException;
import org.example.ojects.BookId;
import org.example.ojects.LoanId;
import org.example.ojects.MemberId;
import org.example.repository.LoanRepository;

import java.time.LocalDate;
import java.util.List;

public class LoanService implements LoanServiceI{


    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }


    @Override
    public void borrowBook(BookId bookId, MemberId memberId, LoanId loanId) {
        if (repository.isBookLoaned(bookId)) {
            throw new BookAlreadyLoanException("Book already loaned");
        }

        if (repository.activeLoansForMember(memberId).size() >= 5) {
            throw new LoanLimitExceededException("Member reached loan limit");
        }

        Loan loan = Loan.create(loanId, bookId, memberId);
        repository.save(loan);
    }

    @Override
    public void returnBook(LoanId loanId) {
        Loan loan = repository.findById(loanId);
        loan.close();
    }

    @Override
    public List<Loan> overdueLoans() {
        return repository.findAll()
                .stream()
                .filter(Loan::isOverdue)
                .toList();
    }
}
