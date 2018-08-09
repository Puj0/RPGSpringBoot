package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.service.ActerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ActerControllerTest {

    private ActerController acterController;

    @Mock
    private ActerService acterService;

    @Mock
    private Model model;

    private MockMvc mockMvc;
    private ApplicationContext ctx;

    private static final String NAME = "Name";
    private static final String NAME2 = "Name2";
    private static final int HEALTH_POINTS = 24;
    private static final int ATTACK = 2;
    private static final int DEFENCE = 1;
    private static final int INITIATIVE = 5;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        acterController = new ActerController(acterService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(acterController).build();
    }

    @Test
    void getActers() {
        List<Acter> acters = new ArrayList<>();
        acters.add(new Animal(NAME, HEALTH_POINTS, ATTACK, DEFENCE, INITIATIVE));
        acters.add(new Animal(NAME2, HEALTH_POINTS, ATTACK, DEFENCE, INITIATIVE));

        when(acterService.getAllActers()).thenReturn(acters);

        assertEquals(2, acterController.getActers().getBody().size());
    }

    @Test
    void addActers() throws Exception {

        List<Acter> acters = new ArrayList<>();
        acters.add(new Animal(NAME, HEALTH_POINTS, ATTACK, DEFENCE, INITIATIVE));

        assertNotNull(this.acterService);
        when(acterService.getAllActers()).thenReturn(acters);

        MvcResult result = mockMvc.perform(post("/acter/addActers/{minNumOfHeroes}", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("acter"))
                .andExpect(model().attribute("acter", hasProperty("id", is(1))))
                .andReturn();

        MockHttpServletResponse mockResponse = result.getResponse();
        assertEquals(mockResponse.getContentType(), "text/html;charset=UTF-8");

        Collection<String> responseHeaders = mockResponse.getHeaderNames();
        assertNotNull(responseHeaders);
        assertEquals(1, responseHeaders.size());
        assertEquals("Check for Content-Type header", "Content-Type", responseHeaders.iterator().next());
        String responseAsString=mockResponse.getContentAsString();
        assertTrue(responseAsString.contains("Spring Framework Guru"));

        verify(acterService, times(1)).getAllActers();
        verifyNoMoreInteractions(acterService);
    }
}