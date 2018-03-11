package br.com.studiotrek.faculdadeimpacta.presentation.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import br.com.studiotrek.faculdadeimpacta.presentation.login.LoginActivity
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager


/**
 * Created by Kleber on 11/03/2018.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(PreferencesManager(this).isUserSet()) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        
        finish()
    }

}