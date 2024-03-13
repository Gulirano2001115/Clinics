package uz.gulirano.clinics.ui.doctor

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.gone
import uz.gulirano.clinics.core.util.visible
import uz.gulirano.clinics.databinding.ScreenDoctorBinding
import uz.gulirano.clinics.ui.main.doctors.DoctorsViewModel


class DoctorScreen : BaseFragment(R.layout.screen_doctor) {

    private val binding by viewBinding(ScreenDoctorBinding::bind)
    private val viewModel by viewModels<DoctorsViewModel>()
    private val args: DoctorScreenArgs by navArgs()
    private var doctorName: String? = null


    override fun onViewCreated() {

        viewModel.getDoctorById(args.id)
        observer()
        loadAction()


    }

    private fun loadAction() {
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addBtn.setOnClickListener {
            findNavController().navigate(
                DoctorScreenDirections.actionDoctorScreenToBronScreen(
                    doctorName!!
                )
            )
        }


    }

    private fun observer() {

        binding.loader.visible()
        viewModel.successDoctorItem.observe(viewLifecycleOwner) { data ->

            data?.let {

                binding.shapeableImageView2.load(data.image)
                binding.name.text = data.fullName
                binding.aboutDoctor.text = data.about
                binding.professiya.text = data.specialization
                binding.odam.text = "Patients ${data.patients}"
                binding.rating.text = "Rating ${data.rating}"
                binding.experince.text = "Patients ${data.experience}"
                doctorName = data.fullName


                binding.linearLayout2.setOnClickListener {
                    val telegram = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://t.me/${data.telegramUser}")
                    )
                    // telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    // telegram.setPackage("org.telegram.messenger")
                    startActivity(telegram)

                }

                binding.phone.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${data.phoneNumber}")
                    startActivity(intent)
                }

                binding.loader.gone()
            }

        }

    }


}