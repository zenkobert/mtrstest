package com.zezenk.mtrstest.service;

import com.zezenk.mtrstest.converter.UserDtoToUser;
import com.zezenk.mtrstest.converter.UserToUserDto;
import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;
import com.zezenk.mtrstest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserDtoToUser userDtoToUserConverter;

    @Mock
    UserToUserDto userToUserDtoConverter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, userDtoToUserConverter, userToUserDtoConverter);
    }

    @Test
    public void whenFindByExistingEmail_thenShouldOptionalPresent() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new TbUser()));
        assertTrue(userService.findByEmail(anyString()).isPresent());
    }

    @Test
    public void whenFindByNonExistingEmail_thenShouldOptionalEmpty() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertFalse(userService.findByEmail(anyString()).isPresent());
    }

    @Test
    public void whenFindByExistingPhoneNumber_thenShouldOptionalPresent() {
        when(userRepository.findByMobileNumber(anyString())).thenReturn(Optional.of(new TbUser()));
        assertTrue(userService.findByMobileNumber(anyString()).isPresent());
    }

    @Test
    public void whenFindByNonExistingPhoneNumber_thenShouldOptionalEmpty() {
        when(userRepository.findByMobileNumber(anyString())).thenReturn(Optional.empty());
        assertFalse(userService.findByMobileNumber(anyString()).isPresent());
    }

    @Test
    public void testSaveNull() {
        assertNull(userService.saveUser(null));
    }

    @Test
    public void testSave() {
        UserDTO userDto = new UserDTO();

        TbUser user = new TbUser();
        user.setId(1L);

        when(userDtoToUserConverter.convert(any(UserDTO.class))).thenReturn(new TbUser());
        when(userRepository.save(any())).thenReturn(user);

        userDto.setId(1L);
        when(userToUserDtoConverter.convert(any(TbUser.class))).thenReturn(userDto);

        UserDTO savedUser = userService.saveUser(userDto);

        assertEquals(Long.valueOf(1L), savedUser.getId());
        verify(userRepository, times(1)).save(any(TbUser.class));
    }

}