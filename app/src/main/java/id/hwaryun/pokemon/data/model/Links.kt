package id.hwaryun.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Links(

    @field:SerializedName("next_page")
    val nextPage: Any? = null,

    @field:SerializedName("first_page")
    val firstPage: String? = null,

    @field:SerializedName("last_page")
    val lastPage: String? = null,

    @field:SerializedName("prev_page")
    val prevPage: Any? = null
)