package uz.gulirano.clinics.core.model.region


import com.google.gson.annotations.SerializedName

data class RegionResponseItem(
    @SerializedName("id")
    val id: Int, // 3
    @SerializedName("region_name")
    val regionName: String // Yashnobod
)