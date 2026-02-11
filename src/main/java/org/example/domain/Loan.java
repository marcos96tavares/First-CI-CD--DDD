package org.example.domain;

import org.example.ojects.LoanId;
import org.example.ojects.BookId;
import org.example.ojects.MemberId;

import java.time.LocalDate;

public class Loan {

    private final LoanId loanId;
    private final BookId bookId;
    private final MemberId memberId;
    private final LocalDate loanDate;
    private LocalDate returnDate;

    private Loan(LoanId loanId, BookId bookId, MemberId memberId) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public static Loan create(LoanId loanId, BookId bookId, MemberId memberId) {
        return new Loan(loanId, bookId, memberId);
    }

    public void close() {
        if (this.returnDate != null) {
            throw new IllegalStateException("Loan already closed");
        }
        this.returnDate = LocalDate.now();
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public boolean isOverdue() {
        return isActive() && loanDate.plusDays(14).isBefore(LocalDate.now());
    }

    public LoanId getLoanId() {
        return loanId;
    }

    public BookId getBookId() {
        return bookId;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
