package com.pegasus.livescore.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pegasus.livescore.database.datasource.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(ApplicationComponent::class)
object BaseModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://77577.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(createOkHttpClient())
//        .client(OkHttpClient.Builder()
//            .addInterceptor(CustomInterceptor())
//            .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS })
//            .build())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        AppDatabase.getDatabase(context)
}

class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .build()
        return chain.proceed(request)
    }
}

private fun createOkHttpClient(): OkHttpClient {
    return try {
        val trustAllCerts: Array<TrustManager> = arrayOf(MyManager())
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .sslSocketFactory(sslContext.getSocketFactory(),MyManager())
            .addInterceptor(logging)
            .addNetworkInterceptor(CustomInterceptor())
            .hostnameVerifier { hostname: String?, session: SSLSession? -> true }
            .build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

class MyManager : X509TrustManager {

    override fun checkServerTrusted(
        p0: Array<out java.security.cert.X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    override fun checkClientTrusted(
        p0: Array<out java.security.cert.X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
        return arrayOf()
    }
}