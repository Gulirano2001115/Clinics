package uz.gulirano.clinics.ui.bottomsheet

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gulirano.clinics.core.util.CreditCardTextWatcher
import uz.gulirano.clinics.core.util.ExpiryDateTextWatcher
import uz.gulirano.clinics.databinding.BottomSheedLayoutBinding

class CardBottomSheet(context: Context) : BottomSheetDialog(context) {

    private val binding by lazy { BottomSheedLayoutBinding.inflate(layoutInflater) }
    var onClickSave: ((
        number: String,
        date: String,
        vcc: String
    ) -> Unit)? = null

    init {
        setContentView(binding.root)
        binding.cardNumberEditText.addTextChangedListener(CreditCardTextWatcher(binding.cardNumberEditText))
        binding.expDateEditText.addTextChangedListener(ExpiryDateTextWatcher(binding.expDateEditText))
        binding.saveButton.setOnClickListener {
            val number = binding.cardNumberEditText.text.toString()
            val date = binding.expDateEditText.text.toString()
            val vcc = binding.cvvEditText.text.toString()

            if (number.isBlank() || date.isBlank() || vcc.isBlank()) {
                return@setOnClickListener
            }

            onClickSave?.invoke(number, date, vcc)
            dismiss()

        }
        show()
    }

}