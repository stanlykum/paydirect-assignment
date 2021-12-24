package com.paydirect.pokedex.controller;

import com.paydirect.pokedex.exception.ImmediateExceptionHandler;
import com.paydirect.pokedex.model.Pokemon;
import com.paydirect.pokedex.service.PokedexService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PokedexController.class)
public class PokedexControllerIntegrationTest {

    @InjectMocks
    private PokedexController pokedexController;

    @MockBean
    private PokedexService pokedexService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Pokemon pokemon;


    @BeforeClass
    public void setup() {

        pokemon = new Pokemon();
        this.mockMvc = MockMvcBuilders.standaloneSetup(pokedexController)
                .setControllerAdvice(new ImmediateExceptionHandler())
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCallPokemonApi() throws Exception {
        Pokemon pokemon = Pokemon.builder().name("mewtwo")
                .description("It was created by\\na scientist after\\nyears of horrific\\fgene splicing and\\nDNA engineering\\nexperiments.")
                .habitat("rare")
                .isLegendary(true)
                .build();

        Mockito.when(pokedexService.findPokemon("mewtwo")).thenReturn(pokemon);
        // Execute the GET request
        mockMvc.perform(get("/pokemon/mewtwo"))
                // Validate the response code and content type
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                // Validate the returned fields
                .andExpect(jsonPath("$", aMapWithSize(4)))
                .andExpect(jsonPath("$.name", is("mewtwo")))
                .andExpect(jsonPath("$.description", is("It was created by\\na scientist after\\nyears of horrific\\fgene splicing and\\nDNA engineering\\nexperiments.")))
                .andExpect(jsonPath("$.habitat", is("rare")))
                .andExpect(jsonPath("$.isLegendary", is(true)));

    }

    @Test
    public void shouldCallFunTranslatorApi() throws Exception {
        Pokemon pokemon = Pokemon.builder().name("mewtwo")
                .description("Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.")
                .habitat("rare")
                .isLegendary(true)
                .build();

        Mockito.when(pokedexService.findTranslation("mewtwo")).thenReturn(pokemon);
        // Execute the GET request
        mockMvc.perform(get("/pokemon/translated/mewtwo"))
                // Validate the response code and content type
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                // Validate the returned fields
                .andExpect(jsonPath("$", aMapWithSize(4)))
                .andExpect(jsonPath("$.name", is("mewtwo")))
                .andExpect(jsonPath("$.description", is("Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.")))
                .andExpect(jsonPath("$.habitat", is("rare")))
                .andExpect(jsonPath("$.isLegendary", is(true)));

    }

}
