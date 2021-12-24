package com.paydirect.pokedex.service;

import com.paydirect.pokedex.model.Pokemon;

public class TestDataBuilder {
    private TestDataBuilder() {
        //not meant to be instantiated.
    }

    public static Pokemon buildPokemon() {
        Pokemon pokemon = Pokemon.builder().name("mewtwo")
                .habitat("rare").description("It was created by\\na scientist after\\nyears of horrific\\fgene splicing and\\nDNA engineering\\nexperiments.").isLegendary(true).build();
        return pokemon;
    }
}
