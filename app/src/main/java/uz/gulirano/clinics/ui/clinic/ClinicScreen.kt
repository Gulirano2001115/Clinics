package uz.gulirano.clinics.ui.clinic

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.gone
import uz.gulirano.clinics.databinding.ScreenClinicBinding

class ClinicScreen : BaseFragment(R.layout.screen_clinic) {

    private val binding by viewBinding(ScreenClinicBinding::bind)
    private val viewModel by viewModels<ClinicViewModel>()
    private val args by navArgs<ClinicScreenArgs>()

    override fun onViewCreated() {

        viewModel.getDetail(args.id)
        observer()
        setListener()

    }

    private fun setListener() {

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun observer() {

        viewModel.successRegionData.observe(viewLifecycleOwner) { result ->

            result?.let {
                binding.imageView.load(it.image)
                binding.description.text = it.about
                binding.phoneNumber.text = it.phone
                binding.rating.rating = it.rating.toFloat()
                binding.loader.gone()
            }


        }

    }


}