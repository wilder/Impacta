package br.com.studiotrek.faculdadeimpacta.presentation.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import br.com.studiotrek.faculdadeimpacta.domain.entity.Student
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity


/**
 * Created by Kleber on 11/03/2018.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

}