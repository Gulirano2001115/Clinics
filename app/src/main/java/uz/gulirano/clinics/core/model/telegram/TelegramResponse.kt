package uz.gulirano.clinics.core.model.telegram


import com.google.gson.annotations.SerializedName

data class TelegramResponse(
    @SerializedName("about")
    val about: String, // string
    @SerializedName("date")
    val date: String, // string
    @SerializedName("full_name")
    val fullName: String, // string
    @SerializedName("gender")
    val gender: String, // string
    @SerializedName("id")
    val id: Int, // 0
    @SerializedName("time")
    val time: String // string
)