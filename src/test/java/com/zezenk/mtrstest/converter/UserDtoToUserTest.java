package com.zezenk.mtrstest.converter;

import static org.junit.Assert.*;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.Gender;
import com.zezenk.mtrstest.model.TbUser;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class UserDtoToUserTest {

    private static final Long ID_VALUE = 1L;
    private static final String EMAIL = "zenkobert@gmail.com";
    private static final String PHONE_NUMBER = "08123456789";
    private static final String FIRST_NAME = "Albert";
    private static final String LAST_NAME = "Zenko";
    private static final Gender GENDER = Gender.MALE;

    UserDtoToUser converter;

    @Before
    public void setUp() throws Exception {
        converter = new UserDtoToUser();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(converter.convert(new UserDTO()));
    }

    @Test
    public void convert() {
        UserDTO userDto = new UserDTO();
        userDto.setId(ID_VALUE);
        userDto.setEmail(EMAIL);
        userDto.setMobileNumber(PHONE_NUMBER);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setGender(GENDER);
        userDto.setDate("30");
        userDto.setMonth("10");
        userDto.setYear("1993");

        TbUser convertedUser = converter.convert(userDto);

        assertEquals(ID_VALUE, convertedUser.getId());
        assertEquals(EMAIL, convertedUser.getEmail());
        assertEquals(PHONE_NUMBER, convertedUser.getMobileNumber());
        assertEquals(FIRST_NAME, convertedUser.getFirstName());
        assertEquals(LAST_NAME, convertedUser.getLastName());
        assertEquals(GENDER, convertedUser.getGender());


        String fullDate = userDto.getYear() + "-"
                + String.format("%02d", Integer.parseInt(userDto.getMonth())) + "-"
                + String.format("%02d", Integer.parseInt(userDto.getDate()));

        assertEquals(LocalDate.parse(fullDate), convertedUser.getDateOfBirth());
    }

    @Test
    public void whenSomeInfoOfDateOfBirthIsMissing_thenConvertedValueShouldBeNull() {
        UserDTO userDto = new UserDTO();
        userDto.setDate("30");
        userDto.setMonth("none");
        userDto.setYear(null);

        TbUser convertedUser = converter.convert(userDto);

        assertNull(convertedUser.getDateOfBirth());
    }

    @Test
    public void whenGenderIsNotSelected_thenConvertedValueShouldBeNull() {
        UserDTO userDto = new UserDTO();
        userDto.setGender(null);

        TbUser convertedUser = converter.convert(userDto);

        assertNull(convertedUser.getGender());
    }
}