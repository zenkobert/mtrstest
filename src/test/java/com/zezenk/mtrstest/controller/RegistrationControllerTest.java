package com.zezenk.mtrstest.controller;

import com.zezenk.mtrstest.service.UserService;
import com.zezenk.mtrstest.service.UserValidationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RegistrationControllerTest {
    RegistrationController registrationController;

    @Mock
    UserService userService;

    @Mock
    UserValidationService userValidationService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    RedirectAttributes redirectAttributes;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        registrationController = new RegistrationController(userService, userValidationService);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void getRegistrationPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user", "genderMap","dateList","monthList", "yearList"));
    }

    @Test
    public void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/loginpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("login-page"));
    }

    @Test
    public void testRegisterValidUser() throws Exception{
        mockMvc.perform(post("/user/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Albert")
                .param("lastName", "Zenko")
                .param("email", "mail@mail.com")
                .param("mobileNumber", "08123456789")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testRegisterInvalidUser() throws Exception{
        mockMvc.perform(post("/user/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Albert")
                .param("lastName", "Zenko")
                .param("email", "abcdefghh")
        )
                .andExpect(model().attributeExists("user", "genderMap","dateList","monthList", "yearList"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }
}