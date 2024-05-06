package com.ega.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="accounts")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    private double balance;
    private String accountType;
    private String branch;
    private String ifscCode;
    @JsonIgnore
    private String pin;

    private String accountStatus;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
