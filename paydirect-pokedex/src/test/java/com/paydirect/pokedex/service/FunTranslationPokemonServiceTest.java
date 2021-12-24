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

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class FunTranslationPokemonServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FunTranslationPokemonService funTranslationPokemonService = new FunTranslationPokemonService();

    @BeforeEach
    public void setUp() throws URISyntaxException {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Pokemon with fun translation API call")
    public void givenMockingIsDoneByMockito_whenInvokeFunTranslationApi_shouldReturnMockedObject() throws Exception {
        //GIVEN
        Pokemon pokemon = TestDataBuilder.buildPokemon();
        String translatedText = "{\n" +
                "    \"success\": {\n" +
                "        \"total\": 1\n" +
                "    },\n" +
                "    \"contents\": {\n" +
                "        \"translated\": \"Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.\",\n" +
                "        \"text\": \"It was created by\\na scientist after\\nyears of horrific\\fgene splicing and\\nDNA engineering\\nexperiments.\",\n" +
                "        \"translation\": \"yoda\"\n" +
                "    }\n" +
                "}";

        //WHEN

        Mockito.when(restTemplate.postForObject(any(), any(), eq(String.class)))
                .thenReturn(translatedText);

        Pokemon response = funTranslationPokemonService.invokeFunTranslationApi(pokemon, "yodaUri");
        System.out.println("Translated response after api call" + response);

        //THEN
        assertEquals(response.getDescription(), "Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.");

    }
}
