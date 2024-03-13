package uz.gulirano.clinics.core.util

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.model.category.CategoryModel

fun animationTransactionClearStack(clearFragmentID: Int): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.from_right).setExitAnim(R.anim.to_left)
        .setPopEnterAnim(R.anim.from_left).setPopExitAnim(R.anim.to_right)
        .setPopUpTo(clearFragmentID, true)
    return navBuilder
}

fun animationTransactionStack(): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.from_right).setExitAnim(R.anim.to_left)
        .setPopEnterAnim(R.anim.from_left).setPopExitAnim(R.anim.to_right)
    return navBuilder
}


fun setAnimation(
    context: Context?,
    viewToAnimate: View,
    duration: Long = 150,
    list: FloatArray = floatArrayOf(0.0f, 1.0f, 0.0f, 1.0f),
    pivot: Pair<Float, Float> = 0.5f to 0.5f
) {

    val anim = ScaleAnimation(
        list[0],
        list[1],
        list[2],
        list[3],
        Animation.RELATIVE_TO_SELF,
        pivot.first,
        Animation.RELATIVE_TO_SELF,
        pivot.second
    )
    anim.duration = (duration * 1f).toLong()
    anim.setInterpolator(context, R.anim.over_shoot)
    viewToAnimate.startAnimation(anim)
}

fun dismissKeyboard(view: View?) {
    view?.let {
        ViewCompat.getWindowInsetsController(view)?.hide(WindowInsetsCompat.Type.ime())
    }
}

fun logMessage(string: String?) {
    Log.e("Error Happened", "-------------------------------------------------------------------")
    Log.e("Error Happened", "--->: ${string.orEmpty()}")
    Log.e("Error Happened", "-------------------------------------------------------------------")
}

fun logError(throwable: Throwable?) {
    Log.e("ApiError", "-------------------------------------------------------------------")
    Log.e("ApiError", "safeApiCall: " + throwable?.localizedMessage)
    Log.e("ApiError", "safeApiCall: " + throwable?.message)
    Log.e("ApiError", "safeApiCall: $throwable")
    throwable?.printStackTrace()
    Log.e("ApiError", "-------------------------------------------------------------------")
}

fun randomColor(): Int {
    val color = listOf(
        Color.parseColor("#24687B"),
        Color.parseColor("#014037"),
        Color.parseColor("#7E1416"),
        Color.parseColor("#989D60"),
        Color.parseColor("#BF5264"),
        Color.parseColor("#542437"),
        Color.parseColor("#329669"),
        Color.parseColor("#3D3251"),
        Color.parseColor("#D85C43"),
        Color.parseColor("#C02944"),
        Color.parseColor("#D3B042"),
    )
    return color.shuffled().first()
}

fun String.getYoutubeFormat(): String {
    return "https://www.youtube.com/embed/"
}

fun View.gone() {
    this.isGone = true
}

fun View.visible() {
    this.isVisible = true
}

fun getDoctorsData(): ArrayList<CategoryModel> {
    val data = ArrayList<CategoryModel>()
    data.add(
        CategoryModel(
            1,
            "Ear, Nose and Throat Specialists",
            Color.parseColor("#FF7D61"),
            R.drawable.ic_1
        )
    )
    data.add(
        CategoryModel(
            2,
            "Gastroenterologists",
            Color.parseColor("#5CB941"),
            R.drawable.ic_2
        )
    )
    data.add(CategoryModel(3, "Dermatologists", Color.parseColor("#FF6161"), R.drawable.ic_3))
    data.add(CategoryModel(4, "Nutritionists", Color.parseColor("#6361FF"), R.drawable.ic_4))
    data.add(CategoryModel(5, "Therapists", Color.parseColor("#B9A841"), R.drawable.ic_5))
    data.add(CategoryModel(6, "Gynecologists", Color.parseColor("#61A6FF"), R.drawable.ic_6))
    data.add(CategoryModel(7, "Physiotherapists", Color.parseColor("#FFA461"), R.drawable.ic_7))
    data.add(CategoryModel(8, "General surgeons", Color.parseColor("#FF61E9"), R.drawable.ic_8))
    data.add(CategoryModel(9, "Dentists", Color.parseColor("#74FF61"), R.drawable.ic_9))
    data.add(CategoryModel(10, "Psychologists", Color.parseColor("#8C61FF"), R.drawable.ic_10))
    data.add(CategoryModel(11, "Pediatrician", Color.parseColor("#FF6161"), R.drawable.ic_11))
    data.add(CategoryModel(12, "Cordiologist", Color.parseColor("#66FF61"), R.drawable.ic_1))

    return data
}


fun getClinicsData(): ArrayList<CategoryModel> {
    val data = ArrayList<CategoryModel>()

    data.add(CategoryModel(1, "Allergology", Color.parseColor("#5CB941"), R.drawable.ic_2_1))
    data.add(CategoryModel(2, "Alternative medicine", Color.parseColor("#418AB9"), R.drawable.ic_2_2))
    data.add(CategoryModel(3, "Hematology", Color.parseColor("#B94141"), R.drawable.ic_2_3))
    data.add(CategoryModel(4, "Vaccination", Color.parseColor("#5CB941"), R.drawable.ic_2_4))
    data.add(CategoryModel(5, "Dermatology", Color.parseColor("#418AB9"), R.drawable.ic_2_5))
    data.add(CategoryModel(6, "Defectology", Color.parseColor("#B94141"), R.drawable.ic_2_6))
    data.add(CategoryModel(7, "Diabetology", Color.parseColor("#5CB941"), R.drawable.ic_2_7))
    data.add(CategoryModel(8, "Dietics", Color.parseColor("#418AB9"), R.drawable.ic_2_8))
    data.add(CategoryModel(9, "Diagnostic center", Color.parseColor("#B94141"), R.drawable.ic_2_9))
    data.add(CategoryModel(10, "Infection diseases", Color.parseColor("#5CB941"), R.drawable.ic_2_10))
    data.add(CategoryModel(11, "Cardiology", Color.parseColor("#418AB9"), R.drawable.ic_2_11))
    data.add(CategoryModel(12, "Cosmetology", Color.parseColor("#B94141"), R.drawable.ic_2_12))
    data.add(CategoryModel(13, "Laboratories", Color.parseColor("#5CB941"), R.drawable.ic_2_13))
    data.add(CategoryModel(14, "Physical therapy", Color.parseColor("#418AB9"), R.drawable.ic_2_14))
    data.add(CategoryModel(15, "ENT(Otolaryngology)", Color.parseColor("#B94141"), R.drawable.ic_2_15))
    data.add(CategoryModel(16, "MRI", Color.parseColor("#5CB941"), R.drawable.ic_2_16))
    data.add(CategoryModel(17, "Neurology", Color.parseColor("#418AB9"), R.drawable.ic_2_17))

    return data
}