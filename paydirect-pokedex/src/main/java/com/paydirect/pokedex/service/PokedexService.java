package com.paydirect.pokedex.service;

import com.paydirect.pokedex.model.Pokemon;

/**
 * This interface used to fetch basic Pokemon and fin translated description response from public Pokemon and Funtranslation API.
 *
 * @author Stanly
 */
public interface PokedexService {
    Pokemon findPokemon(String pokemonName);

    Pokemon findTranslation(String translationType);
}
