package uz.gulirano.clinics.core.model.clinic


import com.google.gson.annotations.SerializedName

data class ClinicResponseItem(
    @SerializedName("about")
    val about: String, // blanc
    @SerializedName("id")
    val id: Int, // 3
    @SerializedName("image")
    val image: String, // https://clinic.salikhdev.uz/clinic/media/files/images/XXXL.webp
    @SerializedName("name")
    val name: String, // Multidisciplinary central clinic
    @SerializedName("phone")
    val phone: String, // +998 71 289 57
    @SerializedName("rating")
    val rating: String, // 4.10
    @SerializedName("region")
    val region: Int // 3
)