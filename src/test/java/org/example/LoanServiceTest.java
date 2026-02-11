package org.example;



import org.example.application.LoanService;
import org.example.exception.BookAlreadyLoanException;
import org.example.exception.LoanLimitExceededException;
import org.example.ojects.BookId;
import org.example.ojects.LoanId;
import org.example.ojects.MemberId;
import org.example.repository.InMemoryLoanRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LoanServiceTest {

    @Test
    public void borrowingLoanedBook_throwsException() {
        InMemoryLoanRepository repo = new InMemoryLoanRepository();
        LoanService service = new LoanService(repo);

        BookId bookId = new BookId("B1");
        MemberId memberId = new MemberId("M1");

        service.borrowBook(bookId, memberId, new LoanId("L1"));

        assertThrows(BookAlreadyLoanException.class,
                () -> service.borrowBook(bookId, memberId, new LoanId("L2")));//2
    }

    @Test
    public void memberCannotHaveMoreThanFiveActiveLoans() {
        InMemoryLoanRepository repo = new InMemoryLoanRepository();
        LoanService service = new LoanService(repo);

        MemberId memberId = new MemberId("M1");

        for (int i = 1; i <= 5; i++) {
            service.borrowBook(
                    new BookId("B" + i),
                    memberId,
                    new LoanId("L" + i)
            );
        }

        assertThrows(LoanLimitExceededException.class,
                () -> service.borrowBook(
                        new BookId("B6"),
                        memberId,
                        new LoanId("L6")));
    }

    @Test
    public void returningBook_closesLoan() {
        InMemoryLoanRepository repo = new InMemoryLoanRepository();
        LoanService service = new LoanService(repo);

        LoanId loanId = new LoanId("L1");

        service.borrowBook(
                new BookId("B1"),
                new MemberId("M1"),
                loanId
        );

        service.returnBook(loanId);

        assertFalse(repo.findById(loanId).isActive());
    }
}
