package uz.gulirano.clinics.ui.dialog

import android.animation.Animator
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.model.telegram.TelegramRequest

class TelegramDialog(context: Context) : Dialog(context) {

    var onClickAccept: ((data: TelegramRequest, dialog: Dialog) -> Unit)? = null

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val v = window!!.decorView
        v.setBackgroundResource(android.R.color.transparent)
        setContentView(R.layout.dialog_enter)
    }

    fun setData(data: TelegramRequest) {

        val textView = findViewById<TextView>(R.id.enterDialogeText)
        textView.text =
            "Name :  ${data.fullName}\nDoctor: ${data.doctorName}\nDate: ${data.date}\nTime: ${data.time}"
        val button = findViewById<AppCompatButton>(R.id.enterButton)
        val animation = findViewById<LottieAnimationView>(R.id.animationView)

        button.setOnClickListener {
            onClickAccept?.invoke(data, this)
            textView.isGone = true
            animation.isVisible = true
            animation.playAnimation()
            animation.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationEnd(animation: Animator) {
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(1000)
                        dismiss()
                    }
                }

                override fun onAnimationCancel(animation: Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(animation: Animator) {
                    TODO("Not yet implemented")
                }

            })

        }

    }

}