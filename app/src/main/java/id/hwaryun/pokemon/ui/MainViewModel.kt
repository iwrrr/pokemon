package id.hwaryun.pokemon.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.hwaryun.pokemon.data.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    fun getPokemon() = repository.getPokemons()

    fun getPokemonById(uuid: String) = repository.getPokemonById(uuid)

    fun getTypes() = repository.types()

    fun getClassifications() = repository.classifications()
}