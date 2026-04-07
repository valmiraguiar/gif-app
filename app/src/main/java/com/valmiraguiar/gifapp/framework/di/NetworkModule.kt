package com.valmiraguiar.gifapp.framework.di

import com.valmiraguiar.gifapp.BuildConfig
import com.valmiraguiar.gifapp.framework.network.constants.NetworkConstants.CONNECT_TIMEOUT_SECONDS
import com.valmiraguiar.gifapp.framework.network.constants.NetworkConstants.READ_TIMEOUT_SECONDS
import com.valmiraguiar.gifapp.framework.network.interceptor.AuthorizationInterceptor
import com.valmiraguiar.gifapp.framework.network.remote.GifApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }

            )
        }
    }

    @Provides
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor(
            apiKey = BuildConfig.API_KEY
        )
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): GifApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(GifApi::class.java)
    }
}
