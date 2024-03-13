package uz.gulirano.clinics.ui.clinic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gulirano.clinics.core.base.BaseViewModel
import uz.gulirano.clinics.core.model.clinic.ClinicResponseItem
import uz.gulirano.clinics.core.repository.DetailRepository
import uz.gulirano.clinics.core.util.ResultWrapper

class ClinicViewModel : BaseViewModel() {

    private val repository = DetailRepository()

    private val _successRegion: MutableLiveData<ClinicResponseItem?> = MutableLiveData()
    val successRegionData: LiveData<ClinicResponseItem?> = _successRegion


    fun getDetail(id: Int) {

        launch {
            when (val result = repository.getDetailById(id)) {
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

}