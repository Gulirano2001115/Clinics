package uz.gulirano.clinics.core.repository

import kotlinx.coroutines.Dispatchers
import uz.gulirano.clinics.core.model.telegram.TelegramRequest
import uz.gulirano.clinics.core.model.telegram.TelegramResponse
import uz.gulirano.clinics.core.network.ApiClient
import uz.gulirano.clinics.core.util.ResultWrapper
import uz.gulirano.clinics.core.util.parseResponse

class TelegramRepository {

    private val api = ApiClient.createTelegramService()

    suspend fun sendTelegram(body: TelegramRequest): ResultWrapper<TelegramResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.sendTelegram(body)
        }
    }


}