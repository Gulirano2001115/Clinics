package uz.gulirano.clinics.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import uz.gulirano.clinics.core.model.help.HelpResponse

interface HelpService {

    @GET("/api/helps/")
    suspend fun loadHelp(): Response<HelpResponse?>

}