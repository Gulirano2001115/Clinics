package uz.gulirano.clinics.ui.main.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gulirano.clinics.core.base.BaseViewModel
import uz.gulirano.clinics.core.model.help.HelpResponse
import uz.gulirano.clinics.core.repository.HelpRepository
import uz.gulirano.clinics.core.util.ResultWrapper

class HelpViewModel : BaseViewModel() {

    private val repository = HelpRepository()

    private val _successRegion: MutableLiveData<HelpResponse?> = MutableLiveData()
    val successRegionData: LiveData<HelpResponse?> = _successRegion


    fun getHelp() {

        launch {
            when (val result = repository.getHelp()) {
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