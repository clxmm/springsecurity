package org.clxmm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author clx
 * @date 2020-06-21 22:00
 * 测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void whenUploadSuccess() {


        try {
            String s = mockMvc.perform(get("/user")
                    .param("username", "clx")
                    .param("age", "1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))

                    // 期望返回的结果
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(3))
                    .andReturn().getResponse().getContentAsString();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void querySuccess() {
        try {
            String s = mockMvc.perform(get("/user/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.username").value("tom")).andReturn().getResponse().getContentAsString();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryGetInfoFail() {
        String s = null;
        try {
            mockMvc.perform(get("/user/a")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);

    }


    @Test
    public void whenCreateSuccess() {
        try {

            Date date = new Date();
            System.out.println(date.getTime());
            String count = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";

            String s = mockMvc.perform(post("/user")
                    .contentType(MediaType.APPLICATION_JSON_UTF8).content(count))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("1"))
                    .andReturn().getResponse().getContentAsString();

            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Test
    public void whenUpdateSuccess() {
        try {

            Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            System.out.println(date.getTime());
            String count = "{\"id\":\"1\", \"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";

            String s = mockMvc.perform(put("/user/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8).content(count))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("3"))
                    .andReturn().getResponse().getContentAsString();

            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }



    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
