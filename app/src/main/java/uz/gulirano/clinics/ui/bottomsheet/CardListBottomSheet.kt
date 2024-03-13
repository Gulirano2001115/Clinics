package uz.gulirano.clinics.ui.bottomsheet

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gulirano.clinics.core.adapter.CardClickAdapter
import uz.gulirano.clinics.core.db.CardEntity
import uz.gulirano.clinics.databinding.CardListBottomSheedLayoutBinding

class CardListBottomSheet(context: Context) : BottomSheetDialog(context) {

    private val binding by lazy { CardListBottomSheedLayoutBinding.inflate(layoutInflater) }
    private val adapter by lazy { CardClickAdapter() }
    var onClickAdd: (() -> Unit)? = null

    fun setData(data: List<CardEntity>) {
        adapter.setData(data)
    }

    init {
        setContentView(binding.root)
        binding.listView.adapter = adapter
        binding.addCard.setOnClickListener {
            dismiss()
            onClickAdd?.invoke()
        }
        adapter.setClick()
        show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.pay.setOnClickListener {
            binding.animationView.visibility = View.VISIBLE
            binding.listView.visibility = View.GONE
            binding.addCard.visibility = View.GONE
            binding.animationView.playAnimation()
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                dismiss()
                val telegram = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://t.me/gulirano_clinic_bot")
                )
                context.startActivity(telegram)
            }
        }
    }


}