package uz.gulirano.clinics.core.repository

import kotlinx.coroutines.Dispatchers
import uz.gulirano.clinics.core.model.clinic.ClinicResponse
import uz.gulirano.clinics.core.model.region.RegionResponse
import uz.gulirano.clinics.core.network.ApiClient
import uz.gulirano.clinics.core.util.ResultWrapper
import uz.gulirano.clinics.core.util.parseResponse

class HomeRepository {

    private val api = ApiClient.createHomeService()

    suspend fun getRegions(): ResultWrapper<RegionResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.loadRegions()
        }
    }

    suspend fun getClinicsByRegionId(name: String, category:String): ResultWrapper<ClinicResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            api.loadClinicByRegion(name , category)
        }
    }


}