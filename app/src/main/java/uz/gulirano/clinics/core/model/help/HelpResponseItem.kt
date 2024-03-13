package uz.gulirano.clinics.core.model.help


import com.google.gson.annotations.SerializedName

data class HelpResponseItem(
    @SerializedName("description")
    val description: String, // Artificial respiration is one of the elementary resuscitation actions that every person should know. Don't assume that face-to-face breathing is enough to deliver oxygen to a victim. First of all, it is necessary to check the airways and, if necessary, remove any object, blood or sputum. Do the first 10 breaths at speed, then use a rhythm of 20 breaths per minute. Remember: if a person's chest is rising, then everything is going well.
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("title")
    val title: String, // Artificial respiration
    @SerializedName("video_url")
    val videoUrl: String // hHK93xcDrHk
)