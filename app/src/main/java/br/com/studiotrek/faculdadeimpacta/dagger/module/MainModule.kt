package br.com.studiotrek.faculdadeimpacta.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Wilder on 18/02/18.
 */
@Module
class MainModule(val application: Application){

    @Provides
    @Singleton
    fun getTextProvider() = application.resources
}