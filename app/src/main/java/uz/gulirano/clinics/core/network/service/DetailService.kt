package uz.gulirano.clinics.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.gulirano.clinics.core.model.clinic.ClinicResponseItem

interface DetailService {

    @GET("/api/clinic/{id}")
    suspend fun getClinicById(@Path("id") id: Int): Response<ClinicResponseItem?>

}