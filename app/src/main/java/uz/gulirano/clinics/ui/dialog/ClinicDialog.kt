package uz.gulirano.clinics.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import coil.load
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.model.ClinicsLocationModel


class ClinicDialog(context: Context, private val model: ClinicsLocationModel) : Dialog(context) {

    private var openGoogleMap: OpenGoogleMap? = null

    fun serGoogleClick(openGoogleMap: OpenGoogleMap) {
        this.openGoogleMap = openGoogleMap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_clinic)
        val v = window!!.decorView
        v.setBackgroundResource(android.R.color.transparent)

        loadData()
        setListeners()
    }

    private fun loadData() {
        findViewById<TextView>(R.id.titleDialog).text = model.name
        findViewById<ImageView>(R.id.imageDialog).load(model.imageUrl) {
            placeholder(R.drawable.placeholder)
        }
        findViewById<TextView>(R.id.phoneDialog).text = "Tel: ${model.phoneNumber}"
        findViewById<RatingBar>(R.id.ratingDialog).rating = model.rating
    }

    private fun setListeners() {
        findViewById<AppCompatButton>(R.id.add_btn).setOnClickListener {
            openGoogleMap?.openGoogleNavigator(model)
        }
    }

    override fun onBackPressed() {
        cancel()
        super.onBackPressed()
    }



}
