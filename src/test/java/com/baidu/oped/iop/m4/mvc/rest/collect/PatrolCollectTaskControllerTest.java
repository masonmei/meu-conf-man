package com.baidu.oped.iop.m4.mvc.rest.collect;

import static com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTaskMethod.HTTP;
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
import com.baidu.oped.iop.m4.domain.entity.collect.PatrolCollectTask;
import com.baidu.oped.iop.m4.domain.repository.collect.PatrolCollectTaskRepository;
import com.baidu.oped.iop.m4.mvc.dto.collect.PatrolCollectTaskDto;

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
public class PatrolCollectTaskControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private PatrolCollectTaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

        taskRepository.deleteAll();
        for (int i = 0; i < 15; i++) {
            PatrolCollectTask task = new PatrolCollectTask();
            task.setProductName("productName");
            task.setAppName("appName");
            task.setName("taskName" + i);
            task.setCycle(60);
            task.setTarget("8080:/test" + i);
            task.setComment("comment" + i);
            task.setMethod(HTTP);
            task.setPort(80);
            task.setTimeout(2000);
            taskRepository.save(task);
        }
    }

    @Test
    public void createPatrolCollectTask() throws Exception {
        PatrolCollectTaskDto task = new PatrolCollectTaskDto();
        task.setName("taskName1");
        task.setCycle(60);
        task.setTarget("8080:/test");
        task.setComment("comment");
        task.setMethod(HTTP);
        task.setPort(80);
        task.setTimeout(2000);
        mvc.perform(post("/products/{productName}/apps/{appName}/patrolCollectTasks", "productName1", "appName1").accept(
                        MediaType.APPLICATION_JSON_UTF8)
                        .with(user("user").password("123456"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(serialize(task)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName1")))
                .andExpect(jsonPath("$.data.id", notNullValue()));
    }

    @Test
    public void deletePatrolCollectTask() throws Exception {
        mvc.perform(delete("/products/{productName}/apps/{appName}/patrolCollectTasks/{taskName}", "productName",
                "appName", "taskName1").with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updatePatrolCollectTask() throws Exception {
        PatrolCollectTaskDto task = new PatrolCollectTaskDto();
        task.setName("taskName");
        task.setCycle(60);
        task.setTarget("8080:/test");
        task.setComment("comment");
        task.setMethod(HTTP);
        task.setPort(80);
        task.setTimeout(2000);
        mvc.perform(put("/products/{productName}/apps/{appName}/patrolCollectTasks/{taskName}", "productName",
                "appName", "taskName1")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serialize(task)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("taskName")))
                .andExpect(jsonPath("$.data.comment", is("comment")))
                .andExpect(jsonPath("$.data.version", is(1)));
    }

    @Test
    public void findProcessCollectTask() throws Exception {
        mvc.perform(get("/products/{productName}/apps/{appName}/patrolCollectTasks/{taskName}", "productName",
                "appName", "taskName1")
                .with(user("user").password("123456"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("taskName1")))
                .andExpect(jsonPath("$.data.comment", is("comment1")))
                .andExpect(jsonPath("$.data.target", is("8080:/test1")));
    }

    @Test
    public void findPatrolCollectTasks() throws Exception {
        mvc.perform(get("/products/{productName}/apps/{appName}/patrolCollectTasks", "productName", "appName")
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

        mvc.perform(get("/products/{productName}/apps/{appName}/patrolCollectTasks", "productName", "appName")
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

        mvc.perform(get("/products/{productName}/apps/{appName}/patrolCollectTasks", "productName", "appName")
                .param("query", "name:taskName1")
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

        mvc.perform(get("/products/{productName}/apps/{appName}/patrolCollectTasks", "productName", "appName")
                .param("query", "taskName1")
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

        mvc.perform(get("/products/{productName}/apps/{appName}/patrolCollectTasks", "productName", "appName")
                .param("query", "8080:/test1")
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