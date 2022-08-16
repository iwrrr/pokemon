package id.hwaryun.pokemon.data.repository

import androidx.lifecycle.LiveData
import id.hwaryun.pokemon.data.model.Pokemon
import id.hwaryun.pokemon.data.model.Type
import id.hwaryun.pokemon.util.Result

interface PokemonRepository {

    fun getPokemons(): LiveData<Result<List<Pokemon>>>

    fun getPokemonById(uuid: String): LiveData<Result<Pokemon>>

    fun storePokemon()

    fun updatePokemon()

    fun deletePokemon()

    fun types(): LiveData<Result<List<Type>>>

    fun classifications()
}