package uz.gulirano.clinics.ui.dialog

import uz.gulirano.clinics.core.model.ClinicsLocationModel

interface OpenGoogleMap {
    fun openGoogleNavigator(clinicsLocationModel: ClinicsLocationModel)
}