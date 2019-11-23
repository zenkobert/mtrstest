package com.zezenk.mtrstest.controller;

import com.zezenk.mtrstest.dto.GenderMapDTO;
import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.Gender;
import com.zezenk.mtrstest.model.TbUser;
import com.zezenk.mtrstest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Slf4j
public class RegistrationController {

    private static final String USER_REGISTRATION_URL = "registration";
    private static final String LOGIN_PAGE_URL = "loginpage";
    private static final String INDONESIA_NO_REGEX = "^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$";
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    private static GenderMapDTO genderMap = new GenderMapDTO();
    private static Map<String, Gender> map = new LinkedHashMap<>();
    private static List<String> dateList = new ArrayList<>();
    private static List<String> monthList = new ArrayList<>();
    private static List<String> yearList = new ArrayList<>();

    static {
        map.put("Male", Gender.MALE);
        map.put("Female", Gender.FEMALE);
        genderMap.setMaps(map);

        for (int i = 1; i <= 31; i++) dateList.add(i + "");
        for (int i = 1; i <= 12; i++) monthList.add(Month.of(i).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        for (int i = Year.now().getValue(); i >= 1900; i--) yearList.add(i + "");
    }

    @RequestMapping({"", "/", "/registration"})
    public String getRegistrationPage(@ModelAttribute("validatedUser") final UserDTO validatedUser, final Model model) {

        if (validatedUser.getId() == null && validatedUser.getMobileNumber() == null && validatedUser.getEmail() == null) {
            model.addAttribute("user", new UserDTO());
        } else {
            model.addAttribute("user", validatedUser);
        }

        initDefaultAttributes(model);

        return USER_REGISTRATION_URL;
    }

    @RequestMapping("/loginpage")
    public String getLoginPage() {
        return LOGIN_PAGE_URL;
    }

    @PostMapping("user/new")
    public String registerUser(@Valid @ModelAttribute("user") final UserDTO user,
                               final BindingResult bindingResult,
                               final Model model,
                               final RedirectAttributes redirectAttributes) {


        if (user.getEmail() != null) validateEmail(user, bindingResult);
        if (user.getMobileNumber() != null) validatePhoneNumber(user, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            model.addAttribute("user", user);
            initDefaultAttributes(model);

            return "registration";
        }

        UserDTO savedUser = userService.saveUser(user);
        redirectAttributes.addFlashAttribute("validatedUser", savedUser);

        return "redirect:/";
    }

    private void initDefaultAttributes(Model model) {
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("dateList", dateList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("yearList", yearList);
    }

    private void validatePhoneNumber(UserDTO user, BindingResult bindingResult) {
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
            return;
        }

        Optional<TbUser> optionalUser = userService.findByMobileNumber(user.getMobileNumber());

        if (optionalUser.isPresent()) {
            FieldError error = new FieldError("user", "mobileNumber", "The number has already been registered");
            bindingResult.addError(error);
        }
    }

    private void validateEmail(UserDTO user, BindingResult bindingResult) {
        user.setEmail(user.getEmail().toLowerCase().trim());

        Optional<TbUser> optionalUser = userService.findByMobileNumber(user.getMobileNumber());

        if (optionalUser.isPresent()) {
            FieldError error = new FieldError("user", "email", "The email has already been registered");
            bindingResult.addError(error);
        }
    }
}
