package com.scm.user.management.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void registeruser_success() throws  Exception{
        MvcResult result = this.mockMvc.perform(post("/registeruser")
                .contentType("application/json")
                .content("{\n" +
                        "\t\"userName\" : \"chithra\",\n" +
                        "\t\"firstName\" : \"Chithra\",\n" +
                        "\t\"lastName\" : \"Kala\",\n" +
                        "\t\"dob\" : \"1963-06-03\",\n" +
                        "\t\"pwd\" : \"Sep@20\",\n" +
                        "\t\"roleCode\" : \"CUSTOMER\",\n" +
                        "\t\"addresses\" : [{\n" +
                        "\t\t\"addressLineOne\":\"F4-12\",\n" +
                        "\t\t\"addressLineTwo\":\"Chennai\",\n" +
                        "\t\t\"state\":\"TN\",\n" +
                        "\t\t\"country\":\"India\",\n" +
                        "\t\t\"pinCode\" : 600073,\n" +
                        "\t\t\"email\" : \"ck@gmail.com\",\n" +
                        "\t\t\"phone\" : \"9123456784\"\n" +
                        "\t}]\n" +
                        "\t\n" +
                        "}"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertFalse(result.getResponse().getContentAsString().contains("\"userDetail\":null"));
        Assert.assertTrue(result.getResponse().getContentAsString().contains("\"errors\":[]"));
    }

    @Test
    public void registeruser_badrequest() throws  Exception{
        MvcResult result = this.mockMvc.perform(post("/registeruser")
                .contentType("application/json")
                .content("{\n" +
                        "\t\"userName\" : \"null\",\n" +
                        "\t\"firstName\" : \"null\",\n" +
                        "\t\"lastName\" : \"Kala\",\n" +
                        "\t\"dob\" : \"1963-06-03\",\n" +
                        "\t\"pwd\" : \"null\",\n" +
                        "\t\"roleCode\" : \"CUSTOMER\",\n" +
                        "\t\"addresses\" : [{\n" +
                        "\t\t\"addressLineOne\":\"F4-12\",\n" +
                        "\t\t\"addressLineTwo\":\"Chennai\",\n" +
                        "\t\t\"state\":\"null\",\n" +
                        "\t\t\"country\":\"India\",\n" +
                        "\t\t\"pinCode\" : 600073,\n" +
                        "\t\t\"email\" : \"ck@gmail.com\",\n" +
                        "\t\t\"phone\" : \"9123456784\"\n" +
                        "\t}]\n" +
                        "\t\n" +
                        "}"))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertTrue(result.getResponse().getContentAsString().contains("\"userDetail\":null"));
        Assert.assertTrue(result.getResponse().getContentAsString().contains("\"UM-FV01\""));
    }

    @Test
    public void validateuser_positive() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/checkuserexists/1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals("true",result.getResponse().getContentAsString());
    }

    @Test
    public void validateuser_negative() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/checkuserexists/10"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals("false",result.getResponse().getContentAsString());
    }
}
