package br.com.studiotrek.faculdadeimpacta.presentation.menu

import android.content.Context
import android.widget.Toast
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

    fun showToast(context : Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    interface View{

    }

}