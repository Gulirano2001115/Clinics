package uz.gulirano.clinics.core.repository

import kotlinx.coroutines.Dispatchers
import uz.gulirano.clinics.core.model.help.HelpResponse
import uz.gulirano.clinics.core.network.ApiClient
import uz.gulirano.clinics.core.util.ResultWrapper
import uz.gulirano.clinics.core.util.parseResponse

class HelpRepository {

    private val api = ApiClient.createHelpService()

    suspend fun getHelp(): ResultWrapper<HelpResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.loadHelp()
        }
    }


}