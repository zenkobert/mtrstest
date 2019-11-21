package com.zezenk.mtrstest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class TbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;
    private String firstName;
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Email
    private String email;

}
