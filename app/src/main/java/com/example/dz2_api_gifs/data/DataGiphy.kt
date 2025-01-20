package com.example.dz2_api_gifs.data

data class DataGiphy(
    val data: List<Giphy>
)

data class Giphy(
    val id: String,
    val images: Images
)

data class Images(
    val original: Original
)

data class Original(
    val url: String
)
