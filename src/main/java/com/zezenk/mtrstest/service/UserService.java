package com.zezenk.mtrstest.service;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;

import java.util.Optional;

public interface UserService {

    Optional<TbUser> findByEmail(String email);

    Optional<TbUser> findByMobileNumber(String mobileNumber);

    UserDTO saveUser(UserDTO user);
}
