package org.example.entity;

import org.example.ojects.MemberId;

import java.time.LocalDate;

public class Member {

    private MemberId memberId;
    private String name;
    private LocalDate registeredAt;


    public Member(MemberId memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.registeredAt = LocalDate.now();
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }
}
