package de.propra.web;

import de.propra.service.RoomService;
import de.propra.service.WorkplaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    WorkplaceService workplaceService;

    @MockBean
    RoomService roomService;

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

    @Test
    @DisplayName("If Date-input is ignored, there will be suitable validation")
    public void test_4() throws Exception {
        MvcResult result = mvc.perform(post("/roommate/user/bookings/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/new"))
                .andExpect(model().errorCount(2))
                .andExpect(model().attributeErrorCount("information", 2))
                .andExpect(model().attributeHasFieldErrors("information", "startTime", "endTime"))
                .andReturn();

        String html = result.getResponse().getContentAsString();
        assertThat(html).contains("Startzeitpunkt angeben!");
        assertThat(html).contains("Endzeitpunkt angeben!");
    }




}
