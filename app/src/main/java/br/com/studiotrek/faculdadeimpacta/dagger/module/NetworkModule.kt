package br.com.studiotrek.faculdadeimpacta.dagger.module

/**
 * Created by Wilder on 18/02/18.
 */
import br.com.studiotrek.faculdadeimpacta.App
import dagger.Module
import dagger.Provides
import okhttp3.Cache
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
    fun provideHttpCache(application: App): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.cache(cache)
        
        client.addInterceptor { chain ->

            try {
                chain.proceed(chain.request())
            } catch (e: Exception) {
                val offlineRequest = chain.request().newBuilder()
                        .header("Cache-Control", "public, only-if-cached," +
                                "max-stale=" + 60 * 60 * 6)
                        .build()
                chain.proceed(offlineRequest)
            }

        }
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://impactaservice.herokuapp.com/")
                .build()
    }

}