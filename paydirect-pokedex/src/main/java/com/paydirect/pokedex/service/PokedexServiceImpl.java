package com.paydirect.pokedex.service;

import com.paydirect.pokedex.model.Pokemon;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This service class reponsbile for invoking public Pokemon and Funtranslation API.
 *
 * @author Stanly
 */
@Service
@Slf4j
public class PokedexServiceImpl implements PokedexService {

    @Autowired
    private RestTemplate restTemplateCall;

    @Autowired
    @Value("${pokemon.uri}")
    private String pokemonUri;
    @Autowired
    @Value("${yoda.translation.uri}")
    private String yodaUri;

    @Autowired
    @Value("${shakespeare.translation.uri}")
    private String shakespeareUri;

    @Autowired
    private BasicPokeMonService basicPokeMonService;

    @Autowired
    private FunTranslationPokemonService funTranslationPokemonService;


    /**
     * This method is used to invoke public Pokemon API.
     * 1.name,desciption,habitat and legendary attributes are generated and return back to caller.
     *
     * @param pokemonName
     * @return pokemon basic information
     */
    @SneakyThrows
    @Override
    public Pokemon findPokemon(String pokemonName) {
        Pokemon pokemon = basicPokeMonService.invokePokemonApi(pokemonName, pokemonUri);
        return pokemon;
    }

    /**
     * This is used to translate the description by invoking FunTranslation Api
     * 1. It invokes public Pokemon API and generate name,descroptions,habitat and legendary attributes are generated.
     * 2. Description is passed to Funtranslation API.
     * 3. If pokemon's habitat is cave or legendary Pokemon then apply Yoda translation.
     * 4. For all other Pokemon, apply Shakespear translation.
     *
     * @param pokemonName
     * @return Pokemon basic information with fun translation of the Pokemon description
     */

    @SneakyThrows
    @Override
    public Pokemon findTranslation(String pokemonName) {
        Pokemon pokemon = basicPokeMonService.invokePokemonApi(pokemonName, pokemonUri);
        if ("cave".equals(pokemon.getHabitat()) || pokemon.isLegendary() == true) {
            funTranslationPokemonService.invokeFunTranslationApi(pokemon, yodaUri);
        } else {
            funTranslationPokemonService.invokeFunTranslationApi(pokemon, shakespeareUri);
        }
        return pokemon;
    }
}
