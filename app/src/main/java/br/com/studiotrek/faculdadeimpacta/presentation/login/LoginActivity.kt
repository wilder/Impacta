package br.com.studiotrek.faculdadeimpacta.presentation.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.entity.Student
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import com.jakewharton.rxbinding.view.visibility
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginPresenter.View {

    @Inject
    lateinit var presenter: LoginPresenter
    private val TAG: String = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)
    }

    fun doLogin(view: View) {
        pbLogin.visibility = View.VISIBLE


        if (etRa.text.toString().isBlank()) {
            etRa.error = getString(R.string.required_field)
            return
        }

        if (etPassword.text.toString().isBlank()) {
            etPassword.error = getString(R.string.required_field)
            return
        }

        presenter.login(etRa.text.toString(), etPassword.text.toString())
    }

    override fun successfulLogin(cookie: LoginResponse) {
        PreferencesManager(this).cookie = CookieDTO(cookie.cookie)
        PreferencesManager(this).user = Student(null, null, etRa.text.toString(), null, etPassword.text.toString())
        Log.d(TAG, "User logged in")

        pbLogin.visibility = View.INVISIBLE
        startActivity(Intent(this@LoginActivity, MenuActivity::class.java))
        finish()
    }

    override fun badLogin(errorMessage: String) {
        Log.d(TAG, "Couldn't log user in")
        pbLogin.visibility = View.INVISIBLE
        Toast.makeText(this, "Login invalido", Toast.LENGTH_SHORT).show()
    }

}
