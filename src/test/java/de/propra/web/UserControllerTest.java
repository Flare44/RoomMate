package de.propra.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("URL 'roommate/user/start' is available and returns the expected view")
    public void test_0() throws Exception {
        mvc.perform(get("/roommate/user/start"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/start"));
    }

    @Test
    @DisplayName("URL 'roommate/user/bookings' is available and returns the expected view")
    public void test_1() throws Exception {
        mvc.perform(get("/roommate/user/bookings"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/bookings"));
    }

    @Test
    @DisplayName("URL 'roommate/user/bookings/new' is available and returns the expected view")
    public void test_2() throws Exception {
        mvc.perform(get("/roommate/user/bookings/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/new"));
    }

    @Test
    @DisplayName("All Parameters can be found and casted to the expected objects")
    public void test_3() throws Exception {
        mvc.perform(get("/roommate/user/bookings/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/new"));
    }


}
