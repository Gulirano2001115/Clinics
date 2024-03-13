package uz.gulirano.clinics.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.gulirano.clinics.core.model.doctors.DoctorResponse
import uz.gulirano.clinics.core.model.doctors.DoctorResponseItem

interface DoctorService {

    @GET("api/doctors/by")
    suspend fun loadDoctors(@Query("specialization") category:String): Response<DoctorResponse?>

    @GET("api/doctor/{id}")
    suspend fun loadDoctors(@Path("id") id: Int): Response<DoctorResponseItem?>

}