package id.hwaryun.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse<T>(

    @SerializedName("code")
    val code: Int? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("pagination")
    val pagination: Pagination? = null
)