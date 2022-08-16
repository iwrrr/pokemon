package id.hwaryun.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Pagination(

    @field:SerializedName("per_page")
    val perPage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("current_page")
    val currentPage: Int? = null
)