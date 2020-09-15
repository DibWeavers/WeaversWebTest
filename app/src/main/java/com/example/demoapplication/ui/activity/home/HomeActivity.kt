package com.example.demoapplication.ui.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapplication.DemoApplication.Companion.getPref
import com.example.demoapplication.R
import com.example.demoapplication.localDatabase.staticValue.StaticValue
import com.example.demoapplication.ui.BaseActivity
import com.example.demoapplication.ui.activity.home.adapter.MainAdapter
import com.example.demoapplication.viewModel.home.HomeViewModel
import com.example.demoapplication.viewModel.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class HomeActivity : BaseActivity() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mainAdapter: MainAdapter

    private lateinit var headerMap: HashMap<String, String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*ViewModel*/
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        list_main.layoutManager = LinearLayoutManager(this)

        headerMap = HashMap()

        if(this::homeViewModel.isInitialized){

            /*Hit Api*/
            getPref().getStringValue(StaticValue.headerKey)?.let {

                headerMap[StaticValue.Header] = "application/json"
                headerMap["ACCESSTOKEN"] = it
                homeViewModel.requestResponse(
                    headerMap,
                    timeZone = "Asia/Kolkata",
                    pageNo= 1,
                    action = "homePage"
                )
            }

            /*Get-Data*/
            homeViewModel.mutableLiveData.observe(this, androidx.lifecycle.Observer {
                mainAdapter =
                    MainAdapter(
                        context = this@HomeActivity,
                        list= it.postList
                    )
                list_main.adapter = mainAdapter
            })
        }


    }

    override fun setContentView(): Int? {
        return R.layout.activity_main
    }


}