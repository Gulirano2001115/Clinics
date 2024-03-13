package uz.gulirano.clinics.ui.consultation

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.model.telegram.TelegramRequest
import uz.gulirano.clinics.databinding.ScreenConsultationBinding
import uz.gulirano.clinics.ui.dialog.TelegramDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ConsultationScreen : BaseFragment(R.layout.screen_consultation) {

    private val binding by viewBinding(ScreenConsultationBinding::bind)
    private val viewModel: TelegramViewModel by viewModels()
    private var dialog: TelegramDialog? = null
    private val args: ConsultationScreenArgs by navArgs()
    private var gender = "Male"

    override fun onViewCreated() {

        loadAction()
        observer()

    }

    private fun observer() {

        viewModel.successMessageData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                delay(1500)
                findNavController().popBackStack()
            }
        }

    }

    private fun loadAction() {
        dialog = TelegramDialog(requireContext())

        binding.datePicker.setOnClickListener {

            val builder = datePicker()
                .setTitleText("Select a date")
                .setPositiveButtonText("ok")
                .build()
            builder.show(childFragmentManager, builder.toString())

            builder.addOnPositiveButtonClickListener { selection ->

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selection

                // Format the date as per your requirement
                val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)

                binding.datePicker.setText(formattedDate)

            }

        }
        binding.timePiker.setOnClickListener {

            val builder = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .build()

            builder.show(childFragmentManager, builder.toString())

            builder.addOnPositiveButtonClickListener {

                val hour: Int = builder.hour
                val minute: Int = builder.minute
                val time = String.format("%02d:%02d:%02d", hour, minute, 0)

                binding.timePiker.setText(time)

            }
        }
        binding.femaleChip.setOnClickListener {
            gender = "Female"
        }
        binding.maleChip.setOnClickListener {
            gender = "Male"
        }
        binding.payBtn.setOnClickListener {

            val date = binding.datePicker.text.toString()
            val time = binding.timePiker.text.toString()
            val name = binding.fullNameEditText.text.toString()
            val problem = binding.problemEditText.text.toString()

            if (date.isEmpty() || date.isBlank()) {
                binding.datePicker.error = "Enter"
                return@setOnClickListener
            }

            if (time.isEmpty() || time.isBlank()) {
                binding.timePiker.error = "Enter"
                return@setOnClickListener
            }

            if (name.isEmpty() || name.isBlank() || name.length < 5) {
                binding.fullNameEditText.error = "Enter"
                return@setOnClickListener
            }

            if (problem.isEmpty() || problem.isBlank()) {
                binding.problemEditText.error = "Enter"
                return@setOnClickListener
            }

            val body = TelegramRequest(problem, date, name, args.doctorName, gender, time)

            dialog?.let {
                it.onClickAccept = { data, _ ->
                    viewModel.sendTelegram(data)
                }
            }

            dialog?.setData(body)
            dialog?.show()


        }


    }

}