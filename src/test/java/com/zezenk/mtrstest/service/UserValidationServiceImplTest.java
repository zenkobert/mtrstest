package com.zezenk.mtrstest.service;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class UserValidationServiceImplTest {

    @Mock
    UserService userService;

    @Mock
    UserValidationService userValidationServiceMock;

    @Mock
    BindingResult bindingResult;

    UserValidationService userValidationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userValidationService = new UserValidationServiceImpl(userService);
    }

    @Test
    public void whenPhoneNumberExist_thenShouldReturnFalse() {
        Optional<TbUser> optionalUser = Optional.of(new TbUser());

        when(userService.findByMobileNumber(anyString())).thenReturn(optionalUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setMobileNumber("08123456789");

        assertFalse(userValidationService.validatePhoneNumber(userDTO, bindingResult));
    }

    @Test
    public void whenPhoneNumberNotExistAndValidIndonesianNumber_thenShouldReturnTrue() {
        Optional<TbUser> optionalUser = Optional.empty();

        when(userService.findByMobileNumber(anyString())).thenReturn(optionalUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setMobileNumber("08123456789");

        assertTrue(userValidationService.validatePhoneNumber(userDTO, bindingResult));
    }

    @Test
    public void whenPhoneNumberNotExistAndInValidIndonesianNumber_thenShouldReturnFalse() {
        Optional<TbUser> optionalUser = Optional.empty();

        when(userService.findByMobileNumber(anyString())).thenReturn(optionalUser);

        UserDTO userDTO = new UserDTO();

        userDTO.setMobileNumber("1234567890");
        assertFalse(userValidationService.validatePhoneNumber(userDTO, bindingResult));

        userDTO.setMobileNumber("abcdefghij");
        assertFalse(userValidationService.validatePhoneNumber(userDTO, bindingResult));

        userDTO.setMobileNumber("09123435123");
        assertFalse(userValidationService.validatePhoneNumber(userDTO, bindingResult));

        userDTO.setMobileNumber("0812345678901234567890");
        assertFalse(userValidationService.validatePhoneNumber(userDTO, bindingResult));
    }

    @Test
    public void whenEmailExist_thenShouldReturnFalse() {
        Optional<TbUser> optionalUser = Optional.of(new TbUser());

        when(userService.findByEmail(anyString())).thenReturn(optionalUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@mail.com");

        assertFalse(userValidationService.validateEmail(userDTO, bindingResult));
    }

    @Test
    public void whenEmailNotExist_thenShouldReturnTrue() {
        Optional<TbUser> optionalUser = Optional.empty();

        when(userService.findByEmail(anyString())).thenReturn(optionalUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@mail.com");

        assertTrue(userValidationService.validateEmail(userDTO, bindingResult));
    }


}