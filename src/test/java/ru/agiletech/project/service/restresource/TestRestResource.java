package ru.agiletech.project.service.restresource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.agiletech.project.service.Application;
import ru.agiletech.project.service.application.project.ProjectDTO;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class})
public class TestRestResource {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .build();

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @Test
    public void testFullFlow() throws Exception {
        var project = testPost();

        String key = project.getKey();

        testPutDescription(key);
        testPutLead(key);
        testPutTeammate(key);

        var foundProject = testGet(key);

        assertNotNull(foundProject.getKey());
        assertNotNull(foundProject.getDescription());
        assertNotNull(foundProject.getLead());
        assertNotNull(foundProject.getName());
        assertNotNull(foundProject.getTasks());
        assertNotNull(foundProject.getTeammates());
    }

    private ProjectDTO testPost() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        String leadId = UUID.randomUUID().toString();

        projectDTO.setDescription("Test description");
        projectDTO.setName("Agile Test Software");
        projectDTO.setLead(leadId);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/projects")
                .content(objectMapper.writeValueAsString(projectDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse()
                .getContentAsString();

        return objectMapper.readValue(content, ProjectDTO.class);
    }

    private void testPutDescription(String key) throws Exception {
        String description = "new description";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/projects/{key}?description={value}", key, description));
    }

    private void testPutLead(String key) throws Exception {
        String leadId = UUID.randomUUID().toString();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/projects/{key}/lead/leadId={value}", key, leadId));
    }

    private void testPutTeammate(String key) throws Exception {
        String teammateId = UUID.randomUUID().toString();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/projects/{key}/teammate/teammatesId={value}", key, teammateId));
    }

    private ProjectDTO testGet(String key) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/{key}", key)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse()
                .getContentAsString();

        return objectMapper.readValue(content, ProjectDTO.class);
    }

}
