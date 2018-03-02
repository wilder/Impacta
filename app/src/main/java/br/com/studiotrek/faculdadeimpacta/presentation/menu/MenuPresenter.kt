package br.com.studiotrek.faculdadeimpacta.presentation.menu

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by kleber on 02/03/2018.
 */
class MenuPresenter @Inject constructor(
        val retrofit: Retrofit){

    lateinit var view: View

    fun bindView(view: View) {
        this.view = view
    }

    interface View{

    }

}