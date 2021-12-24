package com.paydirect.pokedex.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.paydirect.pokedex.model.Pokemon;
import com.paydirect.pokedex.model.Text;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class is responsible for invoking Funtranslation API.
 *
 * @author Stanly
 */
@Service
@Slf4j
public class FunTranslationPokemonService {
    @Autowired
    private RestTemplate restTemplateCall;

    /**
     * This is used to translate the description by invoking FunTranslation Api
     * 1. Description is passed to Funtranslation API.
     *
     * @param pokemon
     * @param translationUri
     * @return Fun translated descriptions generated and return back Pokemon details.
     * @throws URISyntaxException
     * @throws JsonProcessingException
     */
    public Pokemon invokeFunTranslationApi(Pokemon pokemon, String translationUri) throws URISyntaxException, JsonProcessingException {
        URI uri = new URI(translationUri);
        Text textBuilder = Text.builder().text(pokemon.getDescription()).build();
        String translatedResponse = restTemplateCall.postForObject(uri, textBuilder, String.class);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(translatedResponse);
        String translation = JsonPath.read(document, "$.contents.translation");
        String text = JsonPath.read(document, "$.contents.text");
        String translatedText = JsonPath.read(document, "$.contents.translated");
        pokemon.setDescription(translatedText);
        log.info("FunTranslation={},text={},description={}", translation, text, translatedText);
        return pokemon;

    }
}
