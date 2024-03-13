package uz.gulirano.clinics.ui.main.doctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gulirano.clinics.core.base.BaseViewModel
import uz.gulirano.clinics.core.model.doctors.DoctorResponse
import uz.gulirano.clinics.core.model.doctors.DoctorResponseItem
import uz.gulirano.clinics.core.repository.DoctorRepository
import uz.gulirano.clinics.core.util.ResultWrapper

class DoctorsViewModel : BaseViewModel() {

    private val repository = DoctorRepository()

    private val _successDoctor: MutableLiveData<DoctorResponse?> = MutableLiveData()
    val successDoctor: LiveData<DoctorResponse?> = _successDoctor

    private val _successDoctorItem: MutableLiveData<DoctorResponseItem?> = MutableLiveData()
    val successDoctorItem: LiveData<DoctorResponseItem?> = _successDoctorItem


    fun getDoctors(category: String) {

        launch {
            when (val result = repository.getDoctors(category)) {
                is ResultWrapper.ErrorResponse -> {
                }

                is ResultWrapper.NetworkError -> {
                }

                is ResultWrapper.Success -> {
                    _successDoctor.value = result.response
                }
            }

        }

    }

    fun getDoctorById(id: Int) {

        launch {
            when (val result = repository.getDoctorById(id)) {
                is ResultWrapper.ErrorResponse -> {
                }

                is ResultWrapper.NetworkError -> {
                }

                is ResultWrapper.Success -> {
                    _successDoctorItem.value = result.response
                }
            }

        }

    }


}