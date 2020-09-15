package com.example.demoapplication.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.demoapplication.R
import com.example.demoapplication.ui.BaseActivity
import com.example.demoapplication.ui.activity.home.HomeActivity
import com.example.demoapplication.viewModel.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginButton.setOnClickListener {
            when {
                TextUtils.isEmpty(userNameOrMobile.text) -> {
                    Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(userPassword.text) -> {
                    Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    /*Hit Api*/
                    loginViewModel.loginApi(
                        email = userNameOrMobile.text.toString(),
                        password = userPassword.text.toString(),
                        action = "login"
                    )
                    my_progressBar.visibility = View.VISIBLE
                }
            }
        }


        skipLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        /*ViewModel*/
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        /*Get-Data*/
        loginViewModel.mutableLiveData.observe(this, androidx.lifecycle.Observer {

            if(it.isSuccess!!){
                startActivity(Intent(this, HomeActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                my_progressBar.visibility = View.GONE
            }else{
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                my_progressBar.visibility = View.GONE
            }
        })

    }

    override fun setContentView(): Int? {
        return R.layout.activity_login
    }
}
