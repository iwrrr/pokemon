package id.hwaryun.pokemon.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import id.hwaryun.pokemon.data.model.Pokemon
import id.hwaryun.pokemon.data.model.Type
import id.hwaryun.pokemon.data.network.ApiService
import id.hwaryun.pokemon.util.Result
import retrofit2.HttpException
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonRepository {

    override fun getPokemons(): LiveData<Result<List<Pokemon>>> = liveData {
        emit(Result.Loading())

        try {
            val response = apiService.getPokemons(10, 1)
            emit(Result.Success(response.data))
        } catch (e: HttpException) {
            emit(Result.Error(e.message()))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage?.toString() ?: "Unknown Error"))
        }
    }

    override fun getPokemonById(uuid: String): LiveData<Result<Pokemon>> = liveData {
        emit(Result.Loading())

        try {
            val response = apiService.getPokemon(uuid)
            emit(Result.Success(response.data))
        } catch (e: HttpException) {
            emit(Result.Error(e.message()))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage?.toString() ?: "Unknown Error"))
        }
    }

    override fun storePokemon() {
        TODO("Not yet implemented")
    }

    override fun updatePokemon() {
        TODO("Not yet implemented")
    }

    override fun deletePokemon() {
        TODO("Not yet implemented")
    }

    override fun types(): LiveData<Result<List<Type>>> = liveData {
        emit(Result.Loading())

        try {
            val response = apiService.types(10, 1)
            emit(Result.Success(response.data))
        } catch (e: HttpException) {
            emit(Result.Error(e.message()))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage?.toString() ?: "Unknown Error"))
        }
    }

    override fun classifications() {
        TODO("Not yet implemented")
    }
}