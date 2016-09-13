package com.emedinaa.loginkotlin

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.emedinaa.loginkotlin.model.UserEntity
import com.emedinaa.loginkotlin.request.BackendLessService
import com.emedinaa.loginkotlin.request.entity.LogInRaw
import com.emedinaa.loginkotlin.request.entity.LogInResponse
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response

class MainKActivity : AppCompatActivity() {

    private var servicesApiInterface:BackendLessService?=null
    private var vContainer:View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_k)
        ui()
        setupRetrofit()
    }

    private fun ui() {
        var btnLogIn:Button= findViewById(R.id.btnLogIn) as Button
        var etePassword:EditText= findViewById(R.id.etePassword) as EditText
        var eteEmail:EditText= findViewById(R.id.eteEmail) as EditText
        vContainer= findViewById(R.id.vContainer)

        btnLogIn.setOnClickListener{
            //Toast.makeText(this,"click",Toast.LENGTH_LONG).show()

            var email:String= eteEmail.text.toString().trim()
            var password:String= etePassword.text.toString().trim()

            showLoading()
            sendLogIn(email,password)

        }
    }

    private fun  sendLogIn( email: String,password: String) {

        val logInRaw:LogInRaw= LogInRaw()
        logInRaw.login= email
        logInRaw.password= password

        servicesApiInterface!!.logIn(logInRaw,object:Callback<LogInResponse>{
            override fun success(logInResponse: LogInResponse?, response: Response?) {

                if(logInResponse!!.isSuccess()){
                    Log.v("KT","email "+logInResponse!!.email)
                    val userEntity:UserEntity= UserEntity()
                    userEntity.name= logInResponse.name
                    userEntity.email= logInResponse.email
                    userEntity.objectId= logInResponse.objectId
                    userEntity.token= logInResponse.token
                    gotoDashboard(userEntity)
                }else{
                    showError("Error")
                }

                hideLoading()
            }


            override fun failure(error: RetrofitError?) {
                showError("Error")
                hideLoading()
            }
        })

    }


    private fun showError(message:String){

        var container:View= findViewById(R.id.vContainer)!!
        showMessage(container,message)

    }
    private fun showMessage(container: View, message: String) {

        val snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.RED)
        val sbView = snackbar.getView()
        val textView = sbView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    }

    private fun  gotoDashboard(userEntity: UserEntity) {

        val intent: Intent = Intent(this,DashboardActivity::class.java)
        startActivity(intent)

    }
    private fun showLoading()
    {
        findViewById(R.id.llayLoading)?.setVisibility(View.VISIBLE)
    }

    private fun hideLoading()
    {
        findViewById(R.id.llayLoading)?.setVisibility(View.GONE)
    }

    private  fun setupRetrofit(){

        val retrofit: RestAdapter = RestAdapter.Builder()
                .setEndpoint("http://api.backendless.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()

        servicesApiInterface= retrofit.create(
                BackendLessService::class.java)
    }
}
