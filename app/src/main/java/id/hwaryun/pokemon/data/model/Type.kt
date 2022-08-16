package id.hwaryun.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Type(

    @field:SerializedName("uuid")
    val uuid: String,

    @field:SerializedName("name")
    val name: String
)