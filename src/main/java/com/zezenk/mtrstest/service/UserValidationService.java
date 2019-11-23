package com.zezenk.mtrstest.service;

import com.zezenk.mtrstest.dto.UserDTO;
import org.springframework.validation.BindingResult;

public interface UserValidationService {

    boolean validatePhoneNumber(UserDTO user, BindingResult bindingResult);

    boolean validateEmail(UserDTO user, BindingResult bindingResult);
}
