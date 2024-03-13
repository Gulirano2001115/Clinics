package uz.gulirano.clinics.core.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gulirano.clinics.core.app.App
import uz.gulirano.clinics.core.network.service.DetailService
import uz.gulirano.clinics.core.network.service.DoctorService
import uz.gulirano.clinics.core.network.service.HelpService
import uz.gulirano.clinics.core.network.service.HomeService
import uz.gulirano.clinics.core.network.service.TelegramService

object ApiClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://clinic.salikhdev.uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }


    private fun OkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor())
            .addInterceptor(interceptor())
            .build()
    }

    private fun interceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            builder
                .addHeader("Content-Type", "application/json")
            val response = chain.proceed(builder.build())
            response
        }
    }

    private fun chuckerInterceptor() = ChuckerInterceptor.Builder(App.context!!)
        .collector(chuckerCollector())
        .maxContentLength(250_000L)
        .alwaysReadResponseBody(true)
        .build()

    private fun chuckerCollector() = ChuckerCollector(
        context = App.context!!,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )


    // Services
    fun createHomeService(): HomeService {
        return getRetrofit().create(HomeService::class.java)
    }

    fun createHelpService(): HelpService {
        return getRetrofit().create(HelpService::class.java)
    }

    fun createDetailService(): DetailService {
        return getRetrofit().create(DetailService::class.java)
    }

    fun createDoctorService(): DoctorService {
        return getRetrofit().create(DoctorService::class.java)
    }

    fun createTelegramService(): TelegramService {
        return getRetrofit().create(TelegramService::class.java)
    }

}