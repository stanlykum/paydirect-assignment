package com.paydirect.pokedex.controller;

import com.paydirect.pokedex.model.Pokemon;
import com.paydirect.pokedex.service.PokedexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller class responsible for invoking Pokemon public API and Fun Translation API.
 *
 * @author Stanly
 */

@RestController
@RequestMapping("/pokemon")
public class PokedexController {

    @Autowired
    private PokedexService pokedexService;

    /**
     * This method is used to invoke public Pokemon API.
     * 1.name,descroptions,habitat and legendary attributes are generated and return back to caller.
     *
     * @param pokemonName
     * @return pokemon basic information
     */
    @Operation(summary = "Request url for finding basic Pokemon information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetch basic Pokemon information Successfully.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Returns 404 error when Pokemon name is invalid",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "429",
                    description = "Too Many Request to get basic Pokemon information." +
                            " Pokemon public API is limited to execute 5 request per hour for rate limiting",
                    content = @Content)
    })
    @GetMapping("/{pokemonName}")
    public Pokemon findPokemon(@PathVariable String pokemonName) {
        Pokemon pokemon = pokedexService.findPokemon(pokemonName);
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
    @Operation(summary = "This is used to translate the description by invoking FunTranslation Api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Description is translated with yodo or shakespear translation",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Returns 404 error when Pokemon name is invalid",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "429",
                    description = "Too Many Request to get basic Pokemon information." +
                            " Pokemon public API is limited to execute 5 request per hour for rate limiting",
                    content = @Content)
    })
    @GetMapping("/translated/{pokemonName}")
    public Pokemon findPokemonWithTranslation(@PathVariable String pokemonName) {
        Pokemon pokemon = pokedexService.findTranslation(pokemonName);
        return pokemon;
    }
}
