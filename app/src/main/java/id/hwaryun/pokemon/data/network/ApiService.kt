package id.hwaryun.pokemon.data.network

import id.hwaryun.pokemon.data.model.Classification
import id.hwaryun.pokemon.data.model.Pokemon
import id.hwaryun.pokemon.data.model.PokemonResponse
import id.hwaryun.pokemon.data.model.Type
import id.hwaryun.pokemon.util.Endpoints
import retrofit2.http.*

interface ApiService {

    @GET(Endpoints.GET_ALL_POKEMON)
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): PokemonResponse<List<Pokemon>>

    @GET(Endpoints.GET_POKEMON_BY_ID)
    suspend fun getPokemon(
        @Path("uuid") uuid: String
    ): PokemonResponse<Pokemon>

    @POST(Endpoints.STORE_POKEMON)
    suspend fun storePokemon()

    @PUT(Endpoints.UPDATE_POKEMON)
    suspend fun updatePokemon()

    @DELETE(Endpoints.DELETE_POKEMON)
    suspend fun deletePokemon()

    @GET(Endpoints.GET_ALL_TYPES)
    suspend fun types(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): PokemonResponse<List<Type>>

    @GET(Endpoints.GET_ALL_CLASSIFICATION)
    suspend fun classification(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): PokemonResponse<List<Classification>>
}