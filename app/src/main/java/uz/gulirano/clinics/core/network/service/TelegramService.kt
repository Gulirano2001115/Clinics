package uz.gulirano.clinics.core.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gulirano.clinics.core.model.telegram.TelegramRequest
import uz.gulirano.clinics.core.model.telegram.TelegramResponse

interface TelegramService {

    @POST("/api/telegram-send/")
    suspend fun sendTelegram(@Body body: TelegramRequest): Response<TelegramResponse?>

}