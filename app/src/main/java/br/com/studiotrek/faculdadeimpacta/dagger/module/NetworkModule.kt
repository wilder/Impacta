package br.com.studiotrek.faculdadeimpacta.dagger.module

/**
 * Created by Wilder on 18/02/18.
 */
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Wilder on 24/01/17.
 */

@Module
open class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://impactaservice.herokuapp.com/")
                .build()
    }

}