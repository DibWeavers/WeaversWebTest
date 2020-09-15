package com.example.demoapplication.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demoapplication.DemoApplication.Companion.getPref
import com.example.demoapplication.ui.activity.login.LoginActivity
import com.example.demoapplication.R
import com.example.demoapplication.localDatabase.staticValue.StaticValue
import com.example.demoapplication.ui.BaseActivity
import com.example.demoapplication.ui.activity.home.HomeActivity
import com.example.demoapplication.viewModel.splash.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private lateinit var animation: Animation

    private lateinit var splashViewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setAnimation()

        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        splashViewModel.showAnimation()
        splashViewModel.viewAnimationLiveData.observe(this, Observer {
            when (it) {
                is SplashViewModel.ViewAnimation.AnimationView -> {
                    setAnimation()
                }
            }
        })


        splashViewModel.delayIntent()
        splashViewModel.intentLiveData.observe(this, Observer {
            when (it) {
                is SplashViewModel.GotoNextPage.DashBoardActivity -> {
                    if(!TextUtils.isEmpty(getPref().getStringValue(StaticValue.headerKey))) {
                        goToMainActivity()
                    }else{
                        goToLoginActivity()
                    }
                }
            }
        })

    }

    override fun setContentView(): Int? {
        return R.layout.activity_splash
    }

    private fun setAnimation() {
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        nameApp.animation = animation
    }


    private fun goToMainActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }


}
