package uz.gulirano.clinics.core.model.doctors


import com.google.gson.annotations.SerializedName

data class DoctorResponseItem(
    @SerializedName("about")
    val about: String, // Pro conclusionemque conceptam vocent vocent. Dapibus a viderer pertinax legere neque contentiones principes agam. Nam invidunt noster inciderint dolores habeo. Eirmod volutpat dicat sollicitudin interpretaris repudiandae potenti eloquentiam mediocrem voluptatum. Simul homero eum senserit parturient faucibus vestibulum interpretaris sollicitudin curae. Conclusionemque dicta alia feugait semper. Indoctum no nascetur putent reformidans epicurei fermentum tantas.
    @SerializedName("experience")
    val experience: Int, // 4
    @SerializedName("full_name")
    val fullName: String, // Muhammadsolih
    @SerializedName("price")
    val price: String,
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // https://clinic.salikhdev.uz/clinic/media/files/images/photo_2024-01-19_14-59-47.jpg

    @SerializedName("patients")
    val patients: Int, // 12

    @SerializedName("phone_number")
    val phoneNumber: String, // +998909405577

    @SerializedName("rating")
    val rating: String, // 4.50

    @SerializedName("specialization")
    val specialization: String, // Psixolog

    @SerializedName("telegram_user")
    val telegramUser: String // @salikhdev
)