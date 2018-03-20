package br.com.studiotrek.faculdadeimpacta.dagger.module.network

/**
 * Created by Wilder on 18/02/18.
 */
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
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
    fun provideApiServiceHolder() = ApiServiceHolder()

    @Provides
    @Singleton
    fun provideAuthenticator(apiServiceHolder: ApiServiceHolder, application: App) : Authenticator{
        return TokenAuthenticator(apiServiceHolder, application)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache, authenticator: Authenticator): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.cache(cache)
        client.authenticator(authenticator)
        client.addInterceptor(logging)

        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://impactaservice.herokuapp.com")
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit, serviceHolder: ApiServiceHolder): ImpactaApi {
        val api = retrofit.create(ImpactaApi::class.java)
        serviceHolder.set(api)
        return api
    }

}