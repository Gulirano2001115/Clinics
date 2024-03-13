package uz.gulirano.clinics.ui.main.clinics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gulirano.clinics.core.base.BaseViewModel
import uz.gulirano.clinics.core.model.clinic.ClinicResponse
import uz.gulirano.clinics.core.model.region.RegionResponse
import uz.gulirano.clinics.core.repository.HomeRepository
import uz.gulirano.clinics.core.util.ResultWrapper

class HomeViewModel : BaseViewModel() {

    private val repository = HomeRepository()

    private val _successRegion: MutableLiveData<RegionResponse?> = MutableLiveData()
    val successRegionData: LiveData<RegionResponse?> = _successRegion

    private val _successClinics: MutableLiveData<ClinicResponse?> = MutableLiveData()
    val successClinicsData: LiveData<ClinicResponse?> = _successClinics

    fun getRegions() {

        launch {
            when (val result = repository.getRegions()) {
                is ResultWrapper.ErrorResponse -> {
                }

                is ResultWrapper.NetworkError -> {
                }

                is ResultWrapper.Success -> {
                    _successRegion.value = result.response
                }
            }

        }

    }

    fun getClinicsByRegionId(name: String, category: String) {

        launch {
            when (val result = repository.getClinicsByRegionId(name ,category)) {
                is ResultWrapper.ErrorResponse -> {
                }

                is ResultWrapper.NetworkError -> {
                }

                is ResultWrapper.Success -> {
                    _successClinics.value = result.response
                }
            }

        }

    }


}