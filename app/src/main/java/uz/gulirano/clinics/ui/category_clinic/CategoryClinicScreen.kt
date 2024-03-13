package uz.gulirano.clinics.ui.category_clinic

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.adapter.ClinicCategoryAdapter
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.getClinicsData
import uz.gulirano.clinics.databinding.ScreenCategoryClinicBinding


class CategoryClinicScreen : BaseFragment(R.layout.screen_category_clinic) {

    private val binding by viewBinding(ScreenCategoryClinicBinding::bind)
    private val adapter by lazy { ClinicCategoryAdapter() }
    override fun onViewCreated() {

        setAdapter()
        setData()
        loadListener()

    }

    private fun loadListener() {

        adapter.onClickItem = {
            findNavController().navigate(
                CategoryClinicScreenDirections.actionCategoryClinicScreenToHomePage(
                    it
                )
            )
        }

    }

    private fun setData() {
        adapter.setData(getClinicsData())
    }


    private fun setAdapter() {
        binding.clinicCategory.adapter = adapter
        binding.clinicCategory.setHasFixedSize(true)
    }

}