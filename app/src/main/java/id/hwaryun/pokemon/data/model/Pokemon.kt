package id.hwaryun.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(

    @SerializedName("uuid")
    val uuid: String,

    @SerializedName("number")
    val number: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("avatar")
    val avatar: String? = null,

    @SerializedName("classification")
    val classification: String? = null,

    @SerializedName("resistances")
    val resistances: List<Type>? = null,

    @SerializedName("evolutions")
    val evolutions: List<Pokemon>? = null,

    @SerializedName("types")
    val types: List<Type?>? = null,
)