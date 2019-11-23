package com.zezenk.mtrstest.service;

import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.TbUser;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    private static final String INDONESIA_NO_REGEX = "^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$";

    private final UserService userService;

    public UserValidationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Synchronized
    public boolean validatePhoneNumber(UserDTO user, BindingResult bindingResult) {
        user.setMobileNumber(
                user.getMobileNumber()
                        .trim()
                        .replace(" ", "")
                        .replace("-", "")
                        .replace("+62", "0")
        );

        Pattern p = Pattern.compile(INDONESIA_NO_REGEX);
        Matcher m = p.matcher(user.getMobileNumber());
        if (!m.find() || !user.getMobileNumber().startsWith("08")) {
            FieldError error = new FieldError("user", "mobileNumber", "Please enter valid Indonesian phone number");
            bindingResult.addError(error);
            return false;
        }

        Optional<TbUser> optionalUser = userService.findByMobileNumber(user.getMobileNumber());

        if (optionalUser.isPresent()) {
            FieldError error = new FieldError("user", "mobileNumber", "The number has already been registered");
            bindingResult.addError(error);
            return false;
        }

        return true;
    }

    @Override
    @Synchronized
    public boolean validateEmail(UserDTO user, BindingResult bindingResult) {
        user.setEmail(user.getEmail().toLowerCase().trim());

        Optional<TbUser> optionalUser = userService.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            FieldError error = new FieldError("user", "email", "The email has already been registered");
            bindingResult.addError(error);
            return false;
        }

        return true;
    }
}
