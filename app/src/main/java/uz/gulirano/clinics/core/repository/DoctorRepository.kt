package uz.gulirano.clinics.core.repository

import kotlinx.coroutines.Dispatchers
import uz.gulirano.clinics.core.model.doctors.DoctorResponse
import uz.gulirano.clinics.core.model.doctors.DoctorResponseItem
import uz.gulirano.clinics.core.network.ApiClient
import uz.gulirano.clinics.core.util.ResultWrapper
import uz.gulirano.clinics.core.util.parseResponse

class DoctorRepository {

    private val api = ApiClient.createDoctorService()

    suspend fun getDoctors(category:String): ResultWrapper<DoctorResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.loadDoctors(category)
        }
    }

    suspend fun getDoctorById(id: Int): ResultWrapper<DoctorResponseItem?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.loadDoctors(id)
        }
    }


}