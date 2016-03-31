package com.baidu.oped.iop.m4.mvc.rest.collect;

import static com.baidu.oped.iop.m4.utils.JacksonUtils.serialize;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.baidu.oped.iop.m4.Application;
import com.baidu.oped.iop.m4.domain.entity.collect.ProcessCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.ProcessCollectTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.ProcessCollectTaskDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

/**
 * Test cases for class .
 *
 * @author mason
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
@WebAppConfiguration
public class ProcessCollectTaskControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ProcessCollectTaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

        taskRepository.deleteAll();
        for (int i = 0; i < 15; i++) {
            ProcessCollectTask task = new ProcessCollectTask();
            task.setProductName("productName");
            task.setAppName("appName");
            task.setName("processCollectTaskName" + i);
            task.setCycle(60);
            task.setTarget("/bin/tomcat" + i);
            task.setComment("comment" + i);
            taskRepository.save(task);
        }
    }

    @Test
    public void createProcessCollectTask() throws Exception {
        ProcessCollectTaskDto task = new ProcessCollectTaskDto();
        task.setName("processCollectTaskName1");
        task.setCycle(60);
        task.setTarget("/bin/tomcat");
        task.setComment("comment");
        mvc.perform(
                post("/products/{productName}/apps/{appName}/processCollectTasks", "productName1", "appName1").accept(
                        MediaType.APPLICATION_JSON_UTF8)
                        .with(user("user").password("123456"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(serialize(task)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName1")))
                .andExpect(jsonPath("$.data.id", notNullValue()));
    }

    @Test
    public void deleteProcessCollectTask() throws Exception {
        mvc.perform(delete("/products/{productName}/apps/{appName}/processCollectTasks/{taskName}", "productName",
                "appName", "processCollectTaskName1").with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateProcessCollectTask() throws Exception {
        ProcessCollectTaskDto task = new ProcessCollectTaskDto();
        task.setName("processCollectTaskName");
        task.setCycle(60);
        task.setTarget("/bin/tomcat");
        task.setComment("comment");
        mvc.perform(put("/products/{productName}/apps/{appName}/processCollectTasks/{taskName}", "productName",
                "appName", "processCollectTaskName1")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serialize(task)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("processCollectTaskName")))
                .andExpect(jsonPath("$.data.comment", is("comment")))
                .andExpect(jsonPath("$.data.version", is(1)));
    }

    @Test
    public void findProcessCollectTask() throws Exception {
        mvc.perform(get("/products/{productName}/apps/{appName}/processCollectTasks/{taskName}", "productName",
                "appName", "processCollectTaskName1")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("processCollectTaskName1")))
                .andExpect(jsonPath("$.data.comment", is("comment1")))
                .andExpect(jsonPath("$.data.target", is("/bin/tomcat1")));
    }

    @Test
    public void findProcessCollectTasks() throws Exception {
        mvc.perform(get("/products/{productName}/apps/{appName}/processCollectTasks", "productName", "appName")
                .param("orderBy", "-name")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(15)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/processCollectTasks", "productName", "appName")
                .param("pageNumber", "1")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(15)))
                .andExpect(jsonPath("$.data.pageNumber", is(1)))
                .andExpect(jsonPath("$.data.first", is(false)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/processCollectTasks", "productName", "appName")
                .param("query", "name:processCollectTaskName1")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(6)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/processCollectTasks", "productName", "appName")
                .param("query", "processCollectTaskName1")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(6)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

        mvc.perform(get("/products/{productName}/apps/{appName}/processCollectTasks", "productName", "appName")
                .param("query", "/bin/tomcat1")
                .param("orderBy", "-name")
                .param("pageNumber", "0")
                .param("pageSize", "20")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPages", is(1)))
                .andExpect(jsonPath("$.data.totalElements", is(6)))
                .andExpect(jsonPath("$.data.pageNumber", is(0)))
                .andExpect(jsonPath("$.data.first", is(true)))
                .andExpect(jsonPath("$.data.last", is(true)));

    }
}