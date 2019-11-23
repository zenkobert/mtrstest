package com.zezenk.mtrstest.converter;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.Gender;
import com.zezenk.mtrstest.model.TbUser;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class UserToUserDtoTest {

    private static final Long ID_VALUE = 1L;
    private static final String EMAIL = "zenkobert@gmail.com";
    private static final String PHONE_NUMBER = "08123456789";
    private static final String FIRST_NAME = "Albert";
    private static final String LAST_NAME = "Zenko";
    private static final Gender GENDER = Gender.MALE;

    UserToUserDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new UserToUserDto();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(converter.convert(new TbUser()));
    }

    @Test
    public void convert() {
        TbUser user = new TbUser();
        user.setId(ID_VALUE);
        user.setEmail(EMAIL);
        user.setMobileNumber(PHONE_NUMBER);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setGender(GENDER);
        user.setDateOfBirth(LocalDate.of(1993, Month.OCTOBER, 30));

        UserDTO convertedUser = converter.convert(user);

        assertEquals(ID_VALUE, convertedUser.getId());
        assertEquals(EMAIL, convertedUser.getEmail());
        assertEquals(PHONE_NUMBER, convertedUser.getMobileNumber());
        assertEquals(FIRST_NAME, convertedUser.getFirstName());
        assertEquals(LAST_NAME, convertedUser.getLastName());
        assertEquals(GENDER, convertedUser.getGender());
        assertEquals("1993",user.getDateOfBirth().getYear() + "");
        assertEquals("10",user.getDateOfBirth().getMonthValue() + "");
        assertEquals("30",user.getDateOfBirth().getDayOfMonth() + "");
    }
}