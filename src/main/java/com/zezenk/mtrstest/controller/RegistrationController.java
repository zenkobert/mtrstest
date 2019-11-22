package com.zezenk.mtrstest.controller;

import com.zezenk.mtrstest.dto.GenderMapDTO;
import com.zezenk.mtrstest.dto.UserDTO;
import com.zezenk.mtrstest.model.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.*;

@Controller
public class RegistrationController {

    public static GenderMapDTO genderMap = new GenderMapDTO();
    public static Map<String, Gender> map = new LinkedHashMap<>();
    public static List<String> dateList = new ArrayList<>();
    public static List<String> monthList = new ArrayList<>();
    public static List<String> yearList = new ArrayList<>();

    static {
        map.put("Male", Gender.MALE);
        map.put("Female", Gender.FEMALE);
        genderMap.setMaps(map);

        for (int i = 1; i <= 31; i++) dateList.add(i + "");
        for (int i = 1; i <= 12; i++) monthList.add(Month.of(i).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        for (int i = Year.now().getValue(); i >= 1900; i--) yearList.add(i + "");
    }

    @RequestMapping({"", "/", "/registration"})
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("dateList", dateList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("yearList", yearList);

        return "registration";
    }
}
