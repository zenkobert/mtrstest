package com.zezenk.mtrstest.service;

import com.zezenk.mtrstest.converter.UserDtoToUser;
import com.zezenk.mtrstest.converter.UserToUserDto;
import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;
import com.zezenk.mtrstest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoToUser userDtoToUserConverter;
    private final UserToUserDto userToUserDtoConverter;

    public UserServiceImpl(UserRepository userRepository, UserDtoToUser userDtoToUserConverter, UserToUserDto userToUserDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoToUserConverter = userDtoToUserConverter;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @Override
    public UserDTO saveUser(UserDTO source) {
        TbUser user = userDtoToUserConverter.convert(source);

        TbUser savedUser = null;
        if(user != null) savedUser = userRepository.save(user);

        return userToUserDtoConverter.convert(savedUser);
    }
}
