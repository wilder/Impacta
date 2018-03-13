package br.com.studiotrek.faculdadeimpacta.dagger.module

import br.com.studiotrek.faculdadeimpacta.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Wilder on 12/03/18.
 */

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}