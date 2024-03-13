package uz.gulirano.clinics.core.model.telegram


import com.google.gson.annotations.SerializedName

data class TelegramRequest(
    @SerializedName("about")
    val about: String, // string
    @SerializedName("date")
    val date: String, // string
    @SerializedName("full_name")
    val fullName: String, // string
    @SerializedName("doctor_name")
    val doctorName: String, // string
    @SerializedName("gender")
    val gender: String, // string
    @SerializedName("time")
    val time: String // string
)