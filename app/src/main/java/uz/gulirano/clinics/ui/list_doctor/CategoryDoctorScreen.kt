package uz.gulirano.clinics.ui.list_doctor

import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.adapter.DoctorsAdapter
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.gone
import uz.gulirano.clinics.core.util.visible
import uz.gulirano.clinics.databinding.ScreenListDoctorBinding
import uz.gulirano.clinics.ui.main.doctors.DoctorsViewModel

class CategoryDoctorScreen : BaseFragment(R.layout.screen_list_doctor) {

    private val binding by viewBinding(ScreenListDoctorBinding::bind)
    private val adapter by lazy { DoctorsAdapter() }
    private val args: CategoryDoctorScreenArgs by navArgs()
    private val viewModel by viewModels<DoctorsViewModel>()


    override fun onViewCreated() {

        binding.progressBar.visible()
        viewModel.getDoctors(args.category)
        setAdapter()
        observer()
        loadAcitons()

    }

    private fun loadAcitons() {
        adapter.onClickItem = {
            findNavController().navigate(
                CategoryDoctorScreenDirections.actionCategoryDoctorScreenToDoctorScreen(
                    it
                )
            )
        }

        binding.filterBtn.setOnClickListener {

            val popupMenu = PopupMenu(context, binding.filterBtn)
            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.filter_name -> {
                        adapter.filterName()
                    }

                    R.id.filter_experience -> {
                        adapter.filterExperience()
                    }

                    R.id.filter_rating -> {
                        adapter.filterRating()
                    }

                    R.id.filter_price -> {
                        adapter.filterPrice()
                    }
                }

                true
            }
            popupMenu.show()
        }

    }

    private fun setAdapter() {
        binding.clinicCategory.adapter = adapter
        binding.clinicCategory.layoutManager = GridLayoutManager(context, 2)
    }


    private fun observer() {

        viewModel.successDoctor.observe(viewLifecycleOwner) { result ->
            result?.let {
                adapter.setData(it)
                binding.progressBar.gone()
            }
        }

    }


}