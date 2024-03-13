package uz.gulirano.clinics.ui.main

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.databinding.ScreenMainBinding

class MainScreen : BaseFragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)
    override fun onViewCreated() {

        binding.clinics.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToCategoryClinicScreen())
        }
        binding.doctors.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToPageDoctors())
        }
        binding.help.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToHelpPage())
        }
        binding.locatons.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToLocationPage())
        }

    }


}