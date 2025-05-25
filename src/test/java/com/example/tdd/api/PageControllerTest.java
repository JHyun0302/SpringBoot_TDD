package com.example.tdd.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.tdd.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PageController.class)
@Import(TestSecurityConfig.class)
class PageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_method_테스트1() throws Exception {
        //given

        //when & then
        mockMvc.perform(get("/page"))
                .andExpect(status().isOk())
                .andExpect(view().name("page"))
                .andExpect(model().attributeExists("POSTLIST"));
    }

    @Test
    void post_method_테스트1() throws Exception {
        //given
        String title = "제목";
        String content = "내용";

        //when & then
        mockMvc.perform(post("/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/page"));
    }

}