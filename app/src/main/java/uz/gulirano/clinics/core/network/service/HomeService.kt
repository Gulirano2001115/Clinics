package uz.gulirano.clinics.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gulirano.clinics.core.model.clinic.ClinicResponse
import uz.gulirano.clinics.core.model.region.RegionResponse

interface HomeService {

    @GET("/api/regions/")
    suspend fun loadRegions(): Response<RegionResponse?>

    @GET("/api/clinics/by")
    suspend fun loadClinicByRegion(
        @Query("reg") regName: String,
        @Query("category") category: String
    ): Response<ClinicResponse?>


}