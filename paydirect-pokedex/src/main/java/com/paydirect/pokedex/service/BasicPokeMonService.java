package com.paydirect.pokedex.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.paydirect.pokedex.model.Pokemon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This service class is used to invokd public Pokemon API.
 *
 * @author Stanly
 */
@Service
@Slf4j
public class BasicPokeMonService {

    @Autowired
    private RestTemplate restTemplateCall;

    /**
     * This method is used to invoke public Pokemon API.
     * 1.name,descroptions,habitat and legendary attributes are generated and return back to caller.
     *
     * @param pokemonName
     * @param pokemonUri
     * @return pokemon basic information
     */
    public Pokemon invokePokemonApi(String pokemonName, @Value("${pokemon.uri}") String pokemonUri) throws JsonProcessingException {
        String pokemonResponse = restTemplateCall.getForObject(pokemonUri + pokemonName,
                String.class);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(pokemonResponse);
        Object name = JsonPath.read(document, "$.name");
        Object habitatName = JsonPath.read(document, "$.habitat.name");
        boolean legendary = JsonPath.read(document, "$.is_legendary");
        Object description = JsonPath.read(document, "$.flavor_text_entries[0].flavor_text");
        log.info("Name={},Habitat={},Legendary={},Description={}", name, habitatName, legendary, description);
        Pokemon pokemon = Pokemon.builder().name(String.valueOf(name)).description(String.valueOf(description)).habitat(String.valueOf(habitatName)).isLegendary(legendary).build();
        log.info("Basic Pokemon Api response is name={},descriptions={},habitat={},isLegendary={}", pokemon.getName(), pokemon.getDescription(), pokemon.getHabitat(), pokemon.isLegendary());
        return pokemon;
    }

}
