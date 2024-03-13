package uz.gulirano.clinics.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import uz.gulirano.clinics.R
import uz.gulirano.clinics.core.base.BaseFragment
import uz.gulirano.clinics.core.util.animationTransactionClearStack
import uz.gulirano.clinics.databinding.ScreenSplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated() {
        startHome()
    }


    private fun startHome() {
        lifecycleScope.launchWhenCreated {
            delay(1300)
            findNavController().navigate(
                R.id.mainScreen,
                null,
                animationTransactionClearStack(R.id.splashScreen).build()
            )
        }
    }


}