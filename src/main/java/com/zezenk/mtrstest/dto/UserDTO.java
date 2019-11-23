package com.zezenk.mtrstest.dto;

import com.zezenk.mtrstest.model.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserDTO {

    private Long id;

    @NotNull
    private String mobileNumber;

    @NotNull
    @Size(min = 2, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastName;

    private Gender gender;

    private String date;
    private String month;
    private String year;

    @NotNull
    @Email
    private String email;
}
