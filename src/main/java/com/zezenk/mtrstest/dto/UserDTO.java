package com.zezenk.mtrstest.dto;

import com.zezenk.mtrstest.model.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private Long id;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String date;
    private String month;
    private String year;
    private String email;
}
