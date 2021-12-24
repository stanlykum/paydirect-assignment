package com.paydirect.pokedex.service;

import com.paydirect.pokedex.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BasicPokeMonServiceTest {


    private String pokemonUri = "https://pokeapi.co/api/v2/pokemon-species/";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BasicPokeMonService basicPokeMonService = new BasicPokeMonService();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Basic Pokemon API call")
    public void givenMockingIsDoneByMockito_whenInvokePokemonApi_shouldReturnMockedObject() throws Exception {
        //GIVEN
        Pokemon basicPokemon = TestDataBuilder.buildPokemon();
        String jsonStringPokedexModel = "{\"base_happiness\":0,\"capture_rate\":0,\"color\":null,\"egg_groups\":null,\"evolution_chain\":null,\"evolves_from_species\":null,\"flavor_text_entries\":[{\"flavor_text\":\"It was created by\\\\na scientist after\\\\nyears of horrific\\\\fgene splicing and\\\\nDNA engineering\\\\nexperiments.\",\"language\":null,\"version\":null}],\"form_descriptions\":null,\"forms_switchable\":false,\"gender_rate\":0,\"genera\":null,\"generation\":null,\"growth_rate\":null,\"habitat\":{\"name\":\"rare\",\"url\":null},\"has_gender_differences\":false,\"hatch_counter\":0,\"id\":0,\"is_baby\":false,\"is_legendary\":true,\"is_mythical\":false,\"name\":\"mewtwo\",\"names\":null,\"order\":0,\"pal_park_encounters\":null,\"pokedex_numbers\":null,\"shape\":null,\"varieties\":null,\"_legendary\":true,\"_baby\":false,\"_mythical\":false}";

        //WHEN
        Mockito.when(restTemplate.getForObject(pokemonUri + "mewtwo", String.class))
                .thenReturn(jsonStringPokedexModel);
        Pokemon response = basicPokeMonService.invokePokemonApi("mewtwo", pokemonUri);

        //THEN
        assertEquals(response.getName(), basicPokemon.getName());
        assertEquals(response.getDescription(), basicPokemon.getDescription());
        assertEquals(response.getHabitat(), basicPokemon.getHabitat());
        assertEquals(response.isLegendary(), basicPokemon.isLegendary());

    }
}
