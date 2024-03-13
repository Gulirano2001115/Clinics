package uz.gulirano.clinics.ui.consultation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gulirano.clinics.core.base.BaseViewModel
import uz.gulirano.clinics.core.model.telegram.TelegramRequest
import uz.gulirano.clinics.core.model.telegram.TelegramResponse
import uz.gulirano.clinics.core.repository.TelegramRepository
import uz.gulirano.clinics.core.util.ResultWrapper

class TelegramViewModel : BaseViewModel() {

    private val repository = TelegramRepository()

    private val _successMessage: MutableLiveData<TelegramResponse?> = MutableLiveData()
    val successMessageData: LiveData<TelegramResponse?> = _successMessage


    fun sendTelegram(body: TelegramRequest) {

        launch {
            when (val result = repository.sendTelegram(body)) {
                is ResultWrapper.ErrorResponse -> {
                }

                is ResultWrapper.NetworkError -> {
                }

                is ResultWrapper.Success -> {
                    _successMessage.value = result.response
                }
            }

        }

    }

}