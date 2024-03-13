package uz.gulirano.clinics.core.model

data class ClinicsLocationModel(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val imageUrl: String,
    val rating: Float,
    val phoneNumber: String
)
