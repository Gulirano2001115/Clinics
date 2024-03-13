package uz.gulirano.clinics.core.repository

import kotlinx.coroutines.Dispatchers
import uz.gulirano.clinics.core.model.clinic.ClinicResponseItem
import uz.gulirano.clinics.core.network.ApiClient
import uz.gulirano.clinics.core.util.ResultWrapper
import uz.gulirano.clinics.core.util.parseResponse

class DetailRepository {

    private val api = ApiClient.createDetailService()

    suspend fun getDetailById(id: Int): ResultWrapper<ClinicResponseItem?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.getClinicById(id)
        }
    }


}